package work.soller.ml.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a subscriber object with the following fields:
 *
 * "id":1343965485,
 * "name":"John",
 * "email":"demo@mailerlite.com",
 * "sent":0,
 * "opened":0,
 * "clicked":0,
 * "type":"active",
 * "fields":[
 *      {
 *          "key":"email",
 *          "value":"demo@mailerlite.com",
 *          "type":"TEXT"
 *      },
 *      ...
 *      ],
 * "date_subscribe":null,
 * "date_unsubscribe":null,
 * "date_created":"2016-04-04",
 * "date_updated":null
 */
public class Subscriber extends Model {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String SENT = "sent";
    public static final String OPENED = "opened";
    public static final String CLICKED = "clicked";
    public static final String TYPE = "type";
    public static final String DATE_SUBSCRIBE = "date_subscribe";
    public static final String DATE_UNSUBSCRIBE = "date_unsubscribe";
    public static final String DATE_CREATED = "date_created";
    public static final String DATE_UPDATED = "date_updated";
    public static final String FIELDS = "fields";
    public static final String SIGNUP_IP = "signup_ip";
    public static final String SIGNUP_TIMESTAMP = "signup_timestamp";
    public static final String CONFIRMATION_IP = "confirmation_ip";
    public static final String CONFIRMATION_TIMESTAMP = "confirmation_timestamp";
    public static final String RESEND_AUTORESPONDERS = "resend_autoresponders";
    public static final String RESUBSCRIBE = "resubscribe";

    /**
     * Each subscriber has a type that indicates its state.
     *
     * @link https://developers.mailerlite.com/reference#subscribers
     */
    public enum Type {
        ACTIVE("active"),
        UNSUBSCRIBED("unsubscribed"),
        BOUNCED("bounced"),
        JUNK("junk"),
        UNCONFIRMED("unconfirmed");

        private final String value;

        Type(String code) {
            this.value = code;
        }

        public String getValue() {
            return value;
        }

        public static Type findByValue(String value) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Value expected, got null or blank string.");
            }

            for (Type type : values()) {
                if (type.getValue().equals(value.toLowerCase())) {
                    return type;
                }
            }

            throw new IllegalArgumentException("Subscriber type with value [" + value + "] not found. " +
                    "Possible values are: " + Arrays.toString(values()));
        }
    }

    /**
     * Subscriber id class
     */
    public static class Id extends Identifier{
        public Id(String id) {
            super(id);
        }
    }

    public Subscriber() {
        this(new JSONObject());
    }

    public Subscriber(JSONObject jsonObject) {
        super(jsonObject);
        convertFields();
    }

    public Id getId() {
        return has(ID) ? new Id(getString(ID)) : null;
    }

    public void setId(Id id) {
        set(ID, id != null ? id.getValue() : null);
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        set(NAME, name);
    }

    public String getEmil() {
        return getString(EMAIL);
    }

    public void setEmail(String email) {
        set(EMAIL, email);
    }

    public Long getSent() {
        return getLong(SENT);
    }

    public void setSent(Long sent) {
        set(SENT, sent);
    }

    public Long getOpened() {
        return getLong(OPENED);
    }

    public void setOpened(Long opened) {
        set(OPENED, opened);
    }

    public Long getClicked() {
        return getLong(CLICKED);
    }

    public Type getType() {
        String type = getTypeAsString();
        try {
            return Type.findByValue(type);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void setType(Type type) {
        set(TYPE, type != null ? type.getValue() : null);
    }

    public String getTypeAsString() {
        return getString(TYPE);
    }

    public LocalDate getDateSubscribe() {
        return getDate(DATE_SUBSCRIBE);
    }

    public LocalDateTime getDateUnsubscribe() {
        return getDateTime(DATE_UNSUBSCRIBE);
    }

    public LocalDateTime getDateCreated() {
        return getDateTime(DATE_CREATED);
    }

    public LocalDateTime getDateUpdated() {
        return getDateTime(DATE_UPDATED);
    }

    public String getSignupIp() {
        return getString(SIGNUP_IP);
    }

    public void setSignupIp(String ip) {
        set(SIGNUP_IP, ip);
    }

    public LocalDateTime getSignupTimestamp() {
        return getDateTime(SIGNUP_TIMESTAMP);
    }

    public void setSignupTimestamp(LocalDateTime timestamp) {
        setDateTime(SIGNUP_TIMESTAMP, timestamp);
    }

    public String getConfirmationIp() {
        return getString(CONFIRMATION_IP);
    }

    public void setConfirmationIp(String ip) {
        set(CONFIRMATION_IP, ip);
    }

    public LocalDateTime getConformationTimestamp() {
        return getDateTime(CONFIRMATION_TIMESTAMP);
    }

    public void setConfirmationTimestamp(LocalDateTime time) {
        setDateTime(CONFIRMATION_TIMESTAMP, time);
    }

    public void setField(String key, String value) {
        if (!has(FIELDS)) {
            set(FIELDS, new JSONObject());
        }

        getObject(FIELDS).put(key, value);
    }

    public String getField(String key) {
        return has(FIELDS) ? String.valueOf(getObject(FIELDS).get(key)) : null;
    }

    public Set<String> getFieldKeys() {
        return has(FIELDS) ? getObject(FIELDS).keySet() : Collections.emptySet();
    }

    public void setResendAutoresponders(Boolean value) {
        set(RESEND_AUTORESPONDERS, value);
    }

    public void setResubscribe(Boolean value) {
        set(RESUBSCRIBE, value);
    }

    @Override
    public String toString() {
        return getEmil();
    }

    private void convertFields() {
        if (!has(FIELDS)) {
            return;
        }

        // Replace the fields array (if present) with a simple object
        Object fields = get(FIELDS);
        if (fields instanceof JSONArray) {
            getJSONObject().remove(FIELDS);
            JSONObject jsonObject = new JSONObject();
            set(FIELDS, jsonObject);

            // Map array objects to the json object
            for (Object field : (JSONArray) fields) {
                JSONObject json = (JSONObject) field;
                String key = json.getString(Field.KEY);
                String value = json.getString(Field.VALUE);
                jsonObject.put(key, value);
            }
        }
    }
}
