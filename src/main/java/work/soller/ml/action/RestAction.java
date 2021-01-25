/*
 * Copyright (c) 2021 Sergej Soller (sergej@soller.work)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package work.soller.ml.action;

import java.util.Map;

public interface RestAction {
    /**
     * Defines the available HTTP verbs
     */
    enum Verb {
        GET,
        POST,
        PUT,
        DELETE
    }

    /**
     * @return The HTTP path
     */
    String getPath();

    /**
     * @return One of the HTTP verbs GET, PUT, POST, DELETE
     */
    Verb getVerb();

    /**
     * @return A body object that should be send along with the request
     */
    Object getBody();

    /**
     * @return Action context that provides an action runner and credentials
     * to get authenticated against the REST endpoint.
     */
    RestActionContext getContext();

    /**
     * @return Request parameters
     */
    Map<String, Object> getParams();
}
