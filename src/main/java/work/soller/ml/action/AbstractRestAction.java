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

package work.soller.ml.action;

import work.soller.ml.model.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract implementation of the {@link RestAction} with some
 * fields
 *
 * @param <R> The result type of this action
 */
public abstract class AbstractRestAction<R> implements RestAction {
    private final String path;
    private final Verb verb;
    private final Object body;
    private final RestActionContext context;
    private final Map<String, Object> params = new HashMap<>();

    AbstractRestAction(RestActionContext context, String path, Verb verb, Object body) {
        this.context = context;
        this.path = path;
        this.verb = verb;
        this.body = body;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Verb getVerb() {
        return verb;
    }

    @Override
    public Object getBody() {
        return body;
    }

    @Override
    public RestActionContext getContext() {
        return context;
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    public <L> L getParam(String key, Class<L> type) {
        return params.containsKey(key) ? type.cast(params.get(key)) : null;
    }

    /**
     * @return Executes the action and returns it result.
     */
    public abstract R run() throws RestActionException;

    /**
     * Will be called by subclasses whenever the identifier
     * is needed not be null
     *
     * @param identifier The identifier.
     * @return The identifier as string value
     */
    public static String getId(Identifier identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Identifier expected, got null. Please provide a valid identifier.");
        }

        return identifier.getValue();
    }
}
