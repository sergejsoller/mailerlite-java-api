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

package work.soller.ml.action.subscriber;

import org.json.JSONObject;
import work.soller.ml.action.ListRestAction;
import work.soller.ml.action.RestActionContext;
import work.soller.ml.model.Subscriber;

public class FindSubscribersAction extends ListRestAction<Subscriber> {
    public static final String QUERY = "query";
    public static final String OFFSET = "offset";
    public static final String LIMIT = "limit";
    public static final String MINIMIZED = "minimized";

    public FindSubscribersAction(RestActionContext context) {
        super(context, "/subscribers/search", Verb.GET);
    }

    public FindSubscribersAction query(String query) {
        getParams().put(QUERY, query);
        return this;
    }

    public String getQuery() {
        return getParam(QUERY, String.class);
    }

    public FindSubscribersAction offset(Long offset) {
        getParams().put(OFFSET, offset);
        return this;
    }

    public Long getOffset() {
        return getParam(OFFSET, Long.class);
    }

    public FindSubscribersAction limit(Long limit) {
        getParams().put(LIMIT, limit);
        return this;
    }

    public Long getLimit() {
        return getParam(LIMIT, Long.class);
    }

    public FindSubscribersAction minimized(Boolean minimized) {
        getParams().put(MINIMIZED, minimized);
        return this;
    }

    public Boolean isMinimized() {
        return getParam(MINIMIZED, Boolean.class);
    }

    @Override
    protected Subscriber fromObject(JSONObject jsonObject) {
        return new Subscriber(jsonObject);
    }
}
