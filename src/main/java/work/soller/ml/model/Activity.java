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

package work.soller.ml.model;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Activity extends Model {
    public static final String DATE = "date";
    public static final String REPORT_ID = "report_id";
    public static final String CAMPAIGN_ID = "campaign_id";
    public static final String SUBJECT = "subject";
    public static final String TYPE = "type";
    public static final String LINK_ID = "link_id";
    public static final String LINK = "link";
    public static final String RECEIVER = "receiver";
    public static final String SENDER = "sender";

    public enum Type {
        OPEN("open", "opens"),
        CLICK("click", "clicks"),
        JUNK("junk", "junks"),
        BOUNCE("bounce", "bounces"),
        UNSUBSCRIBE("unsubscribe", "unsubscribes"),
        FORWARD("forward", "forwards"),
        SEND("sent", "sendings");

        private final String value;
        private final String query;

        Type(String value, String query) {
            this.value = value;
            this.query = query;
        }

        public String getValue() {
            return value;
        }

        public String getQuery() {
            return query;
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

            throw new IllegalArgumentException("Activity type with value [" + value + "] not found. " +
                    "Possible values are: " + Arrays.toString(values()));
        }
    }

    public Activity() {
        this(new JSONObject());
    }

    public Activity(JSONObject object) {
        super(object);
    }

    public LocalDateTime getDate() {
        return getDateTime(DATE);
    }

    public void setDate(LocalDateTime time) {
        setDateTime(DATE, time);
    }

    public Long getReportId() {
        return getLong(REPORT_ID);
    }

    public void setReportId(Long reportId) {
        set(REPORT_ID, reportId);
    }

    public String getSubject() {
        return getString(SUBJECT);
    }

    public void setSubject(String subject) {
        set(SUBJECT, subject);
    }

    public Long getCampaignId() {
        return getLong(CAMPAIGN_ID);
    }

    public Type getType() {
        return has(TYPE) ? Type.findByValue(getString(TYPE)) : null;
    }

    public void setType(Type type) {
        set(TYPE, type != null ? type.getValue() : null);
    }

    public Long getLinkId() {
        return getLong(LINK_ID);
    }

    public String getLink() {
        return getString(LINK);
    }

    public Recipient getReceiver() {
        return has(RECEIVER) ? new Recipient(getObject(RECEIVER)) : null;
    }

    public Recipient getSender() {
        return has(SENDER) ? new Recipient(getObject(SENDER)) : null;
    }

    public String toString() {
        return getType() != null ? getType().getValue() : null;
    }
}
