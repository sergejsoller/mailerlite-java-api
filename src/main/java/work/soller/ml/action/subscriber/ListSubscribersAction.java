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

package work.soller.ml.action.subscriber;

import org.json.JSONObject;
import work.soller.ml.action.ListRestAction;
import work.soller.ml.action.RestActionContext;
import work.soller.ml.model.Subscriber;

public class ListSubscribersAction extends ListRestAction<Subscriber> {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    public static final String TYPE = "type";

    public ListSubscribersAction(RestActionContext context) {
        super(context, "/subscribers", Verb.GET);
    }

    public ListSubscribersAction limit(Long limit) {
        getParams().put(LIMIT, limit);
        return this;
    }

    public ListSubscribersAction offset(Long offset) {
        getParams().put(OFFSET, offset);
        return this;
    }

    public ListSubscribersAction type(Subscriber.Type type) {
        getParams().put(TYPE, type != null ? type.getValue() : null);
        return this;
    }

    public Long getOffset() {
        return getParam(OFFSET, Long.class);
    }

    public Long getLimit() {
        return getParam(LIMIT, Long.class);
    }

    public Subscriber.Type getType() {
        String type = getParam(TYPE, String.class);
        return type != null && !type.isBlank() ? Subscriber.Type.findByValue(type) : null;
    }

    @Override
    protected Subscriber fromObject(JSONObject jsonObject) {
        return new Subscriber(jsonObject);
    }
}
