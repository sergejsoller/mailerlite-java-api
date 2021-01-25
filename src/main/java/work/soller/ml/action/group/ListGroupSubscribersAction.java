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

package work.soller.ml.action.group;

import org.json.JSONObject;
import work.soller.ml.action.ListRestAction;
import work.soller.ml.action.RestActionContext;
import work.soller.ml.model.Group;
import work.soller.ml.model.Subscriber;

public class ListGroupSubscribersAction extends ListRestAction<Subscriber> {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    public static final String FILTER = "filter";

    public ListGroupSubscribersAction(RestActionContext context, Group.Id groupId) {
        super(context, "/groups/" + getId(groupId) + "/subscribers", Verb.GET);
    }

    public ListGroupSubscribersAction(RestActionContext context, Group.Id groupId, Subscriber.Type type) {
        super(context, "/groups/" + getId(groupId) + "/subscribers/" + type.getValue(), Verb.GET);
    }

    public ListGroupSubscribersAction limit(Long limit) {
        if (limit != null && limit > 5000) {
            limit = 5000L;
        }
        getParams().put(LIMIT, limit);
        return this;
    }

    public ListGroupSubscribersAction offset(Long offset) {
        getParams().put(OFFSET, offset);
        return this;
    }

    public ListGroupSubscribersAction filter(String filter) {
        getParams().put(FILTER, filter);
        return this;
    }

    public Long getOffset() {
        return getParam(OFFSET, Long.class);
    }

    public Long getLimit() {
        return getParam(LIMIT, Long.class);
    }

    public String getFilter() {
        return getParam(FILTER, String.class);
    }

    @Override
    protected Subscriber fromObject(JSONObject jsonObject) {
        return new Subscriber(jsonObject);
    }
}
