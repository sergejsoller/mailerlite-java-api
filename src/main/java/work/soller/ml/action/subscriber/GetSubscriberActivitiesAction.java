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
import work.soller.ml.model.Activity;
import work.soller.ml.model.Subscriber;

public class GetSubscriberActivitiesAction extends ListRestAction<Activity> {

    public GetSubscriberActivitiesAction(RestActionContext context, Subscriber.Id subscriberId) {
        super(context, "/subscribers/" + getId(subscriberId) + "/activity", Verb.GET);
    }

    public GetSubscriberActivitiesAction(RestActionContext context, Subscriber.Id subscriberId, Activity.Type type) {
        super(context, "/subscribers/" + getId(subscriberId) + "/activity/" + type.getQuery(), Verb.GET);
    }

    @Override
    protected Activity fromObject(JSONObject jsonObject) {
        return new Activity(jsonObject);
    }
}
