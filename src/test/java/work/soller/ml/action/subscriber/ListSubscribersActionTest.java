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

package work.soller.ml.action.subscriber;

import org.junit.Before;
import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;
import work.soller.ml.action.AbstractRestAction;
import work.soller.ml.model.Filter;
import work.soller.ml.model.Subscriber;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ListSubscribersActionTest extends AbstractActionTest {
    ListSubscribersAction action;

    @Before
    public void setup() {
        this.action = new ListSubscribersAction(context);
    }

    @Test
    public void testPathAndVerb() {
        assertEquals("/subscribers", action.getPath());
        assertEquals(AbstractRestAction.Verb.GET, action.getVerb());
    }

    @Test
    public void testParams() {
        // When
        action.limit(1L)
                .offset(2L)
                .type(Subscriber.Type.ACTIVE)
                .filter(new Filter(Filter.DATE_SUBSCRIBE, Filter.Operator.GT, LocalDate.of(2020, 1, 1)));

        // Then
        assertEquals(1L, (long) action.getParams().get("limit"));
        assertEquals(2L, (long) action.getParams().get("offset"));
        assertEquals(Subscriber.Type.ACTIVE.getValue(), action.getParams().get("type"));
        assertEquals("2020-01-01", action.getParams().get("filter[date_subscribe][$gt]"));
    }
}