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

package work.soller.ml.endpoint;

import work.soller.ml.action.RestActionContext;
import work.soller.ml.action.group.*;
import work.soller.ml.model.Group;
import work.soller.ml.model.Subscriber;

import java.util.Collection;

public class GroupsRestEndpoint implements GroupsEndpoint {
    private final RestActionContext context;

    public GroupsRestEndpoint(RestActionContext context) {
        this.context = context;
    }

    @Override
    public ListGroupsAction list() {
        return new ListGroupsAction(context);
    }

    @Override
    public GetGroupAction get(Group.Id groupId) {
        return new GetGroupAction(context, groupId);
    }

    @Override
    public CreateGroupAction create(Group group) {
        return new CreateGroupAction(context, group);
    }

    @Override
    public UpdateGroupAction update(Group group) {
        return new UpdateGroupAction(context, group);
    }

    @Override
    public DeleteGroupAction delete(Group.Id groupId) {
        return new DeleteGroupAction(context, groupId);
    }

    @Override
    public AddGroupSubscriberAction add(Group.Id groupId, Subscriber subscriber) {
        return new AddGroupSubscriberAction(context, groupId, subscriber);
    }

    @Override
    public ImportGroupSubscribersAction add(Group.Id groupId, Collection<Subscriber> subscribers) {
        return new ImportGroupSubscribersAction(context, groupId, subscribers);
    }

    @Override
    public DeleteGroupSubscriberAction delete(Group.Id groupId, Subscriber.Id subscriberId) {
        return new DeleteGroupSubscriberAction(context, groupId, subscriberId);
    }

    @Override
    public ListGroupSubscribersAction subscribers(Group.Id groupId) {
        return new ListGroupSubscribersAction(context, groupId);
    }

    @Override
    public ListGroupSubscribersAction subscribers(Group.Id groupId, Subscriber.Type subscriberType) {
        return new ListGroupSubscribersAction(context, groupId, subscriberType);
    }

    @Override
    public CountGroupSubscribersAction subscriberCount(Group.Id groupId) {
        return new CountGroupSubscribersAction(context, groupId);
    }

    @Override
    public CountGroupSubscribersAction subscriberCount(Group.Id groupId, Subscriber.Type subscriberType) {
        return new CountGroupSubscribersAction(context, groupId, subscriberType);
    }
}
