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

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Filter {
    public static final Property DATE_SUBSCRIBE = new Property("date_subscribe");
    public static final Property DATE_UNSUBSCRIBE = new Property("date_unsubscribe");
    public static final Property DATE_CREATED = new Property("date_created");
    public static final Property DATE_UPDATED = new Property("date_updated");

    /**
     * A property that is used to get filtered.
     */
    public static class Property {
        private final String name;

        public Property(String name) {
            this.name = name;
        }
    }

    /**
     * The operator to use to compare the value
     */
    public enum Operator {
        GT("$gt"),
        GTE("$gt"),
        LT("$lt"),
        LTE("$lte");

        private final String value;

        Operator(String value) {
            this.value = value;
        }
    }

    /**
     * Filter property
     */
    private final Property property;

    /**
     * Filter operator
     */
    private final Operator operator;

    /**
     * Filter value
     */
    private final String value;

    public Filter(Property property, Operator operator, LocalDate date) {
        this(property, operator, date.format(Model.DATE_FORMAT));
    }

    public Filter(Property property, Operator operator, LocalDateTime timestamp) {
        this(property, operator, timestamp.format(Model.DATE_TIME_FORMAT));
    }

    public Filter(Property property, Operator operator, String value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public String getKey() {
        return "filter[" + property.name + "][" + operator.value + "]";
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
