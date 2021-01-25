# MailerLite Java API
Java wrapper around [MailerLite's V2 REST-API](https://developers.mailerlite.com/reference).

License: [MIT-License](https://tldrlegal.com/license/mit-license)

Note: This library uses [Unirest](https://kong.github.io/unirest-java/) to execute API calls.

## Getting started
```Java
// Create credentials
MailerLiteCredentials credentials = new MailerLiteCredentials("ML_API_KEY");

// Create client
MailerLiteClient client = new UnirestMailerLiteClient(credentials);

// Get subscribers endpoint and fetch list with subscribers
List<Subscriber> subscribers = client.subscribers()
        .list()
        .offset(0L)
        .limit(10L)
        .type(Subscriber.Type.ACTIVE)
        .run();
```

## Current development state

Endpoint   | Verb | Function | Status | Desc
:---|---:|:---|:---:|:---
Subscribers|GET | /subscribers | ✅ | Get account's subscribers
Subscribers|POST | /subscribers | ✅ | Add new single subscriber
Subscribers|GET | /subscribers/:id | ✅ | Get single subscriber
Subscribers|PUT | /subscribers/:id | ✅ | Update single subscriber
Subscribers|GET | /subscribers/search | ✅ | Search for subscribers
Subscribers|GET | /subscribers/:id/groups | ✅ | Get groups subscriber belongs to
Subscribers|GET | /subscribers/:id/activity | ✅ | Get activity (clicks, opens, etc) of selected subscriber
Subscribers|GET | /subscribers/:id/activity/:type | ✅ | Get activity of selected subscriber by specified type (opens, clicks, etc)
&nbsp;|&nbsp;|&nbsp;|&nbsp;|&nbsp;
Groups|GET | /groups | ✅ | Get list of groups
Groups|GET | /groups/:id | ✅ | Get single group by ID
Groups|POST | /groups | ✅ | Create new group
Groups|PUT | /groups/:id | ✅ | Update existing group
Groups|DELETE | /groups/:id | ✅ | Remove group
Groups|POST | /groups/:id/subscribers | ✅️ | Add new single subscriber to specified group
Groups|POST | /groups/:id/subscribers/import | ✅️️ | Add many new subscribers to specified group at once
Groups|GET | /groups/:id/subscribers/import/:import_id | ⁉️️ | Check import state
Groups|GET | /groups/:id/subscribers | ✅️ | Get all subscribers in a specified group
Groups|GET | /groups/:id/subscribers/:type | ✅️ | Get all subscribers in a specified group by its type
Groups|GET | /groups/:id/subscribers/:type/count | ✅️ | Getting a count of subscibers by type in a group
Groups|GET | /groups/:id/subscribers/:subscriber_id | ⁉️️ | **Use of this function is unclear**
Groups|DELETE | /groups/:id/subscribers/:subscriber_id | ✅️ | Remove single subscriber from specified group
&nbsp;|&nbsp;|&nbsp;|&nbsp;|&nbsp;
Fields|GET | /fields | ✅ | Get subscriber fields of account
Fields|POST | /fields | ✅ | Create new custom field in account
Fields|PUT | /fields/:id | ✅ | Update custom field in account
Fields|DELETE | /fields/:id | ✅ | Remove custom field from account

## Examples

**Create a subscriber and group and link them**
```java
// Create a new subscriber
Subscriber subscriber = new Subscriber();
subscriber.setEmail("foo@existsing-domain.com");
subscriber.setName("Foo");
subscriber = client.subscribers()
        .create(subscriber)
        .run();

// Create new group
Group group = new Group();
group.setName("The Foos");
group = client.groups()
        .create(group)
        .run();

// Add subscriber to the group of foos
subscriber = client.groups()
        .add(group.getId(), subscriber)
        .run();

LOGGER.info("Subscriber [" + subscriber + "] added to [" +  group.getName() + "]");
```

**Create and delete a field**
```java
// Find an existing field to delete
String fieldKey = "some_field";
Field field = client.fields()
        .list()
        .run()
        .stream()
        .filter(f -> f.getKey().equals(fieldKey))
        .findFirst()
        .orElse(null);

// If not exists, then create
if (field == null) {
    field = new Field();
    field.setTitle("Some field");
    field.setKey(fieldKey);
    field = client.fields()
            .create(field)
            .run();
}

// And then delete again
Boolean result = client.fields()
        .delete(field.getId())
        .run();
```

**Search for a subscriber and change one of its fields**
```java
// Create a new subscriber
List<Subscriber> searchResult = client.subscribers()
        .find()
        .query("foo@existsing-domain.com")
        .run();

// Get first result
Subscriber subscriber = !searchResult.isEmpty() ? searchResult.get(0) : null;

// Update subscriber field
if (subscriber != null) {
    subscriber.setField("city", "Foo City");
    subscriber = client.subscribers()
            .update(subscriber)
            .run();

    LOGGER.info("Subscriber [" + subscriber + "] is living in [" + subscriber.getField("city") + "]");
}
```