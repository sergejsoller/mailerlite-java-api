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

import work.soller.ml.action.group.*;
import work.soller.ml.model.Group;
import work.soller.ml.model.Subscriber;

import java.util.Collection;

public interface GroupsEndpoint {

    /**
     * Returns an action builder to request MailerLite groups.
     *
     * @return A new instance of the action builder
     */
    ListGroupsAction list();

    /**
     * Returns an action builder to fetching a group with
     * the given group id
     *
     * @param groupId The group id
     * @return The action builder
     */
    GetGroupAction get(Group.Id groupId);

    /**
     * Returns an action builder to create the given group
     *
     * @param group The group instance to create
     * @return The action builder
     */
    CreateGroupAction create(Group group);

    /**
     * Returns an action builder to update the given group.
     *
     * Ensure to provide a valid group id as that will be used to
     * build the endpoint path.
     *
     * @param group The group to update.
     * @return The action builder
     */
    UpdateGroupAction update(Group group);

    /**
     * Returns an action builder to delete a group with the given id.
     *
     * @param groupId The id of the group to delete
     * @return The action builder
     */
    DeleteGroupAction delete(Group.Id groupId);

    /**
     * Returns an action builder to add a single subscriber to a particular group.
     *
     * @param groupId The group id to add the subscriber to
     * @param subscriber The subscriber to add.
     * @return The action builder
     */
    AddGroupSubscriberAction add(Group.Id groupId, Subscriber subscriber);
    /**
     * Returns an action builder to add a collection of subscribers to a particular group.
     *
     * @param groupId The group id to add the subscribers to
     * @param subscribers A collections of subscribers that should be added to the group
     * @return The action builder
     */
    ImportGroupSubscribersAction add(Group.Id groupId, Collection<Subscriber> subscribers);

    /**
     * Returns an action builder to delete a subscriber from a group.
     *
     * @param groupId The group id
     * @param subscriberId The id of the subscriber to delele
     * @return The action builder
     */
    DeleteGroupSubscriberAction delete(Group.Id groupId, Subscriber.Id subscriberId);

    /**
     * Returns an action builder to query all subscribers of a group.
     *
     * @param groupId The group id
     * @return The action builder
     */
    ListGroupSubscribersAction subscribers(Group.Id groupId);

    /**
     * Returns an action builder to query all subscribers of a group by subscriber type
     *
     * @param groupId The group id
     * @param subscriberType The subscriber type to select
     * @return The action builder
     */
    ListGroupSubscribersAction subscribers(Group.Id groupId, Subscriber.Type subscriberType);

    /**
     * Returns an action builder to count all subscribers of a group.
     *
     * @param groupId The group id
     * @return The action builder
     */
    CountGroupSubscribersAction subscriberCount(Group.Id groupId);

    /**
     * Returns an action builder to count all subscribers of a group by subscriber type
     *
     * @param groupId The group id
     * @param subscriberType The subscriber type to select
     * @return The action builder
     */
    CountGroupSubscribersAction subscriberCount(Group.Id groupId, Subscriber.Type subscriberType);
}
