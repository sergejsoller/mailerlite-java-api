
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

import org.json.JSONObject;

import java.time.LocalDateTime;

public class Group extends Model {
    // id | integer | ID of group
    public static final String ID = "id";

    // name | string | Title of group
    public static final String NAME = "name";

    // total | integer | Total count of people in group
    public static final String TOTAL = "total";

    // active | integer | Total count of active people in group
    public static final String ACTIVE = "active";

    // unsubscribed | integer | Total count of unsubscribed people in group
    public static final String UNSUBSCRIBED = "unsubscribed";

    // bounced | integer | Total count of bounced people in group
    public static final String BOUNCED = "bounced";

    // unconfirmed | integer | Total count of unconfirmed people in group
    public static final String UNCONFIRMED = "unconfirmed";

    // junk | integer | Total count of junk people in group
    public static final String JUNK = "junk";

    // sent | integer | Total count of sent emails in a group
    public static final String SENT = "sent";

    // opened | integer | Total count of opens in a group
    public static final String OPENED = "opened";

    // clicked | integer | Total count of clicks in a group
    public static final String CLICKED = "clicked";

    // date_created | string | Date & time when group is created
    public static final String DATE_CREATED = "date_created";

    // date_updated | string | Date & time when group is updated
    public static final String DATE_UPDATED = "date_updated";


    public static class Id extends Identifier {
        public Id(String value) {
            super(value);
        }
    }

    public Group() {
        this(new JSONObject());
    }

    public Group(JSONObject object) {
        super(object);
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

    public void setTotal(Long total) {
        set(TOTAL, total);
    }

    public Long getTotal() {
        return getLong(TOTAL);
    }

    public void setActive(Long active) {
        set(ACTIVE, active);
    }

    public Long getActive() {
        return getLong(ACTIVE);
    }

    public void setUnsubscribed(Long unsubscribed) {
        set(UNSUBSCRIBED, unsubscribed);
    }

    public Long getUnsubscribed() {
        return getLong(UNSUBSCRIBED);
    }

    public void setBounced(Long bounced) {
        set(BOUNCED, bounced);
    }

    public Long getBounced() {
        return getLong(BOUNCED);
    }

    public void setUnconfirmed(Long unconfirmed) {
        set(UNCONFIRMED, unconfirmed);
    }

    public Long getUnconfirmed() {
        return getLong(UNCONFIRMED);
    }

    public void setJunk(Long junk) {
        set(JUNK, junk);
    }

    public Long getJunk() {
        return getLong(JUNK);
    }

    public void setSent(Long sent) {
        set(SENT, sent);
    }

    public Long getSent() {
        return getLong(SENT);
    }

    public void setOpened(Long opened) {
        set(OPENED, opened);
    }

    public Long getOpened() {
        return getLong(OPENED);
    }

    public void setClicked(Long clicked) {
        set(CLICKED, clicked);
    }

    public Long getClicked() {
        return getLong(CLICKED);
    }

    public void setDateCreated(LocalDateTime time) {
        setDateTime(DATE_CREATED, time);
    }

    public LocalDateTime getDateCreated() {
        return getDateTime(DATE_CREATED);
    }

    public void setDateUpdated(LocalDateTime time) {
        setDateTime(DATE_UPDATED, time);
    }

    public LocalDateTime getDateUpdated() {
        return getDateTime(DATE_CREATED);
    }

    @Override
    public String toString() {
        return getName();
    }
}
