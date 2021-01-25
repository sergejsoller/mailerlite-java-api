
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

import work.soller.ml.action.subscriber.*;
import work.soller.ml.model.Activity;
import work.soller.ml.model.Subscriber;

/**
 * Provides methods for accessing MailerLite subscribers.
 */
public interface SubscribersEndpoint {

    /**
     * @return An action builder to request subscribers
     */
    ListSubscribersAction list();

    /**
     * Returns an action builder that counts all subscribers
     *
     * @return The action builder
     */
    CountSubscribersAction count();

    /**
     * Returns a particular subscriber defined by its id or email address.
     *
     * @param idOrEmail The subscribers mail
     * @return An action builder to request a subscriber
     */
    GetSubscriberAction get(Subscriber.Id idOrEmail);

    /**
     * Returns the action builder to create the given subscriber.
     *
     * @param subscriber The subscriber to create
     * @return An action builder to create a subscriber
     */
    CreateSubscriberAction create(Subscriber subscriber);

    /**
     * Returns an action builder to update the given subscriber.
     *
     * Make sure that the given subscriber instance returns a valid id.
     *
     * @param subscriber The subscriber to update
     * @return The action builder to update a subscriber
     */
    UpdateSubscriberAction update(Subscriber subscriber);

    /**
     * Returns an action builder to fetch the groups of a
     * particular subscriber.
     *
     * @param subscriberId The id of the subscriber to fetch groups for.
     * @return The action builder.
     */
    GetSubscriberGroupsAction groups(Subscriber.Id subscriberId);

    /**
     * Returns an action builder for searching subscribers by a query.
     *
     * @return The action builder
     */
    FindSubscribersAction find();

    /**
     * Returns an action builder to retrieve all activities of
     * a particular subscriber
     *
     * @param subscriberId The id of the subscriber to retrieve activities for.
     * @return The action builder
     */
    GetSubscriberActivitiesAction activity(Subscriber.Id subscriberId);

    /**
     * Returns an action builder to retrieve a particular type of activities of
     * a particular subscriber
     *
     * @param subscriberId The id of the subscriber to retrieve activities for.
     * @param type The type of activity to filter for.
     * @return The action builder
     */
    GetSubscriberActivitiesAction activity(Subscriber.Id subscriberId, Activity.Type type);
}
