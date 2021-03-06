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

package work.soller.ml.endpoint;

import work.soller.ml.action.RestActionContext;
import work.soller.ml.action.subscriber.*;
import work.soller.ml.model.Activity;
import work.soller.ml.model.Subscriber;

public class SubscribersRestEndpoint implements SubscribersEndpoint {
    private final RestActionContext context;

    public SubscribersRestEndpoint(RestActionContext context) {
        this.context = context;
    }

    @Override
    public ListSubscribersAction list() {
        return new ListSubscribersAction(context);
    }

    @Override
    public CountSubscribersAction count() {
        return new CountSubscribersAction(context);
    }

    @Override
    public GetSubscriberAction get(Subscriber.Id idOrEmail) {
        return new GetSubscriberAction(context, idOrEmail);
    }

    @Override
    public CreateSubscriberAction create(Subscriber subscriber) {
        return new CreateSubscriberAction(context, subscriber);
    }

    @Override
    public UpdateSubscriberAction update(Subscriber subscriber) {
        return new UpdateSubscriberAction(context, subscriber);
    }

    @Override
    public GetSubscriberGroupsAction groups(Subscriber.Id subscriberId) {
        return new GetSubscriberGroupsAction(context, subscriberId);
    }

    @Override
    public FindSubscribersAction find() {
        return new FindSubscribersAction(context);
    }

    @Override
    public GetSubscriberActivitiesAction activity(Subscriber.Id subscriberId) {
        return new GetSubscriberActivitiesAction(context, subscriberId);
    }

    @Override
    public GetSubscriberActivitiesAction activity(Subscriber.Id subscriberId, Activity.Type type) {
        return new GetSubscriberActivitiesAction(context, subscriberId, type);
    }
}
