/*
 * MIT License
 *
 * Copyright (c) 2021 Sergej Soller <sergej@soller.work>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package work.soller.ml.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.soller.ml.action.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Implements the {@link RestActionRunner} interface by using the Unirest library.
 */
public class UnirestActionRunner implements RestActionRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(UnirestActionRunner.class);
    private static final String ML_API_KEY_HEADER = "X-MailerLite-ApiKey";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    @Override
    public JSONArray run(ListRestAction<?> action) throws RestActionException {
        JsonNode body = runWithJsonResult(action);
        return body != null ? body.getArray() : new JSONArray();
    }

    @Override
    public JSONObject run(ObjectRestAction<?> action) throws RestActionException {
        JsonNode body = runWithJsonResult(action);
        return body != null ? body.getObject() : new JSONObject();
    }

    /**
     * Creates a new request from the given action,
     * executes it and makes sure the response is a successful one.
     *
     * Extracts the result from body as json node and returns the body as result.
     *
     * @param action The action to run.
     * @return The result as JSON node.
     * @throws RestActionException In case of an unknown error.
     */
    protected JsonNode runWithJsonResult(AbstractRestAction<?> action) throws RestActionException {
        HttpRequest request = requestFromAction(action);
        HttpResponse<JsonNode> response = execute(request);
        return response.getBody();
    }

    /**
     * Creates an Unirest request from the given action
     *
     * @param action The action to create the request from
     * @return The created request with headers, params and body applied and ready to be executed.
     */
    protected HttpRequest requestFromAction(AbstractRestAction<?> action) {
        MailerLiteCredentials credentials = action.getContext().getCredentials();
        AbstractRestAction.Verb verb = action.getVerb();
        String url = credentials.getBaseUrl() + action.getPath();

        HttpRequest request;
        switch (verb) {
            case GET:
                request = withParams(Unirest.get(url), action);
                break;
            case POST:
                request = withParams(Unirest.post(url), action);
                break;
            case PUT:
                request = withParams(Unirest.put(url), action);
                break;
            case DELETE:
                request = withParams(Unirest.delete(url), action);
                break;
            default:
                throw new IllegalArgumentException("HTTP verb [" + verb + "] is unknown. Please provide one of the following: GET, POST, PUT, DELETE");
        }

        // If body is given, set it on the request
        Object body = action.getBody();
        if (request instanceof HttpRequestWithBody && body != null) {
            if (body instanceof JSONObject) {
                ((HttpRequestWithBody) request).body((JSONObject) body);
            } else if (body instanceof JSONArray) {
                ((HttpRequestWithBody) request).body((JSONArray) body);
            } else {
                ((HttpRequestWithBody) request).body(body);
            }
        }

        return request;
    }

    /**
     * Executes the given Unirest request and treat each response with status code != 200
     * as error. Thus raise an exception with detailed information about the error.
     *
     * @param request The request to execute
     * @return The response with a JSON node as result
     * @throws RestActionException In case of any error and also status code != 200
     */
    protected HttpResponse<JsonNode> execute(HttpRequest request) throws RestActionException {
        try {
            // Make request
            HttpResponse<JsonNode> response = request.asJson();

            // Check status
            if (!(response.getStatus() >= 200 && response.getStatus() <= 299)) {
                String body = getBodyAsString(response);
                throw new RestActionException("Could not execute request. MailerLite endpoint returned status ["  + response.getStatus() + "]. " +
                        "Request url: " + request.getUrl() + ", " +
                        "status: " + response.getStatus() +", " +
                        "status text: " + response.getStatusText() + ", " +
                        "body: " + body);
            }

            // Success! so return response
            return response;
        } catch (UnirestException e) {
            throw new RestActionException("Could not execute get on [" + request.getUrl() + "]", e);
        }
    }

    /**
     * Reads the body of the unirest response and returns it as string.
     *
     * @param response The response to extract the body string from
     * @return The body string or null in case of an error.
     */
    private String getBodyAsString(HttpResponse<JsonNode> response) {
        InputStream rawBody = null;
        try {
            rawBody = response.getRawBody();
            return rawBody != null ? new String(rawBody.readAllBytes(), StandardCharsets.UTF_8) : "";
        } catch (IOException e) {
            LOGGER.error("Can't read unirest body stream", e);
            return null;
        } finally {
            try {
                if (rawBody != null) {
                    rawBody.close();
                }
            } catch (IOException e) {
                LOGGER.error("Can't close unirest body stream", e);
            }
        }
    }

    /**
     * Applies the default headers and params on the given request.
     *
     * @param request The request to apply the headers & params on.
     * @param action The action to execute
     * @param <R> The request type
     * @return The request with headers & params applied.
     */
    protected <R extends HttpRequest> R withParams(R request, AbstractRestAction<?> action) {
        MailerLiteCredentials credentials = action.getContext().getCredentials();
        request.header(ML_API_KEY_HEADER, credentials.getApiKey());
        request.header(CONTENT_TYPE_HEADER, APPLICATION_JSON);
        request.queryString(action.getParams());
        return request;
    }
}
