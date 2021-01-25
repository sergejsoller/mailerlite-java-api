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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ImportResult extends Model {
    public static final String UNCHANGED = "unchanged";
    public static final String IMPORTED = "imported";
    public static final String UPDATED = "updated";
    public static final String ERRORS = "errors";

    public ImportResult(JSONObject object) {
        super(object);
    }

    public List<Subscriber> getImported() {
        if (has(IMPORTED)) {
            return toSubscribers(getArray(IMPORTED));
        } else {
            return Collections.emptyList();
        }
    }

    public List<Subscriber> getUnchanged() {
        if (has(UNCHANGED)) {
            return toSubscribers(getArray(UNCHANGED));
        } else {
            return Collections.emptyList();
        }
    }

    public List<Subscriber> getUpdated() {
        if (has(UPDATED)) {
            return toSubscribers(getArray(UPDATED));
        } else {
            return Collections.emptyList();
        }
    }

    public List<Subscriber> getErrors() {
        if (has(ERRORS)) {
            return toSubscribers(getArray(ERRORS));
        } else {
            return Collections.emptyList();
        }
    }

    private List<Subscriber> toSubscribers(JSONArray array) {
        return StreamSupport.stream(array.spliterator(), false)
                .map(JSONObject.class::cast)
                .map(Subscriber::new)
                .collect(Collectors.toList());
    }
}
