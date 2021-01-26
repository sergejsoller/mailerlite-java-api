
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

package work.soller.ml.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Model {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
        return value != null && !"null".equals(value) ? String.valueOf(value) : null;
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
