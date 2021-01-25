package work.soller.ml.model;

import org.json.JSONObject;

import java.time.LocalDateTime;

public class Field extends Model {
    public static final String ID = "id";
    public static final String KEY = "key";
    public static final String TITLE = "title";
    public static final String VALUE = "value";
    public static final String TYPE = "type";
    public static final String DATE_CREATED = "date_created";
    public static final String DATE_UPDATED = "date_updated";

    public enum Type {
        TEXT,
        NUMBER,
        DATE
    }

    public static class Id extends Identifier {
        public Id(String id) {
            super(id);
        }
    }

    public Field() {
        this(new JSONObject());
    }

    public Field(JSONObject object) {
        super(object);
    }

    public Id getId() {
        return has(ID) ? new Id(getString(ID)) : null;
    }

    public void setId(Id id) {
        set(ID, id != null ? id.getValue() : null);
    }

    public String getKey() {
        return getString(KEY);
    }

    public void setKey(String key) {
        set(KEY, key);
    }
    public String getValue() {
        return getString(VALUE);
    }

    public void setValue(String value) {
        set(VALUE, value);
    }

    public String getTitle() {
        return getString(TITLE);
    }

    public void setTitle(String title) {
        set(TITLE, title);
    }

    public Type getType() {
        return has(TYPE) ? Type.valueOf(getString(TYPE)) : null;
    }

    public void setType(Type type) {
        set(TYPE, type != null ? type.name() : null);
    }

    public LocalDateTime getDateCreated() {
        return getDateTime(DATE_CREATED);
    }

    public LocalDateTime getDateUpdated() {
        return getDateTime(DATE_UPDATED);
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
