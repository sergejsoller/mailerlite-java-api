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
