package work.soller.ml.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Model {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final JSONObject object;

    protected Model(JSONObject object) {
        if (object == null) {
            throw new IllegalArgumentException("JSONObject expected, got null");
        }

        this.object = object;
    }

    public JSONObject getJSONObject() {
        return object;
    }

    protected String getString(String key) {
        Object value = get(key);
        return value != null ? String.valueOf(value) : null;
    }

    protected Long getLong(String key) {
        String string = getString(key);
        return string != null ? Long.valueOf(string) : null;
    }

    protected LocalDate getDate(String key) {
        return getDate(key, DATE_FORMAT);
    }
    protected void setDate(String key, LocalDate date) {
        setDate(key, date, DATE_FORMAT);
    }

    protected LocalDate getDate(String key, DateTimeFormatter format) {
        String string = getString(key);
        return string != null ? LocalDate.parse(string, format) : null;
    }

    protected void setDate(String key, LocalDate date, DateTimeFormatter format) {
        set(key, date != null ? date.format(format) : null);
    }

    protected LocalDateTime getDateTime(String key) {
        return getDateTime(key, DATE_TIME_FORMAT);
    }

    protected void setDateTime(String key, LocalDateTime time) {
        setDateTime(key, time, DATE_TIME_FORMAT);
    }

    protected LocalDateTime getDateTime(String key, DateTimeFormatter format) {
        String string = getString(key);
        return string != null ? LocalDateTime.parse(string, format) : null;
    }

    protected void setDateTime(String key, LocalDateTime time, DateTimeFormatter format) {
        set(key, time != null ? time.format(format) : null);
    }

    protected boolean has(String key) {
        return getJSONObject().has(key);
    }

    protected Object get(String key) {
        return has(key) ? getJSONObject().get(key) : null;
    }

    protected JSONArray getArray(String key) {
        return has(key) ? (JSONArray) get(key) : null;
    }

    protected JSONObject getObject(String key) {
        return has(key) ? (JSONObject) get(key) : null;
    }

    protected void set(String key, Object value) {
        getJSONObject().put(key, value);
    }
}
