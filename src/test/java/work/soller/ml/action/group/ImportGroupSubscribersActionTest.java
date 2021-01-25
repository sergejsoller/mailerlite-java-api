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

package work.soller.ml.action.group;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;
import work.soller.ml.action.AbstractRestAction;
import work.soller.ml.model.Group;
import work.soller.ml.model.Subscriber;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImportGroupSubscribersActionTest extends AbstractActionTest {
    private ImportGroupSubscribersAction action;
    private JSONObject json1 = new JSONObject();
    private JSONObject json2 = new JSONObject();

    @Before
    public void setup() {

        Subscriber sub1 = new Subscriber(json1);
        Subscriber sub2 = new Subscriber(json2);
        this.action = new ImportGroupSubscribersAction(context, new Group.Id("123"), List.of(sub1, sub2));
    }

    @Test
    public void testParams() {
        // When
        action.resubscribe(true).autoresponder(true).returnStatus(true);

        // Then
        assertEquals(true, action.isAutoresponder());
        assertEquals(true, action.isResubscribe());
        assertEquals(true, action.isReturnStatus());
    }

    @Test
    public void testPathAndVerb() {
        assertPathAndVerb(action, "/groups/123/subscribers/import", AbstractRestAction.Verb.POST);
    }

    @Test
    public void testBody() {
        JSONObject body = (JSONObject) action.getBody();
        assertTrue(body.has("subscribers"));
        assertEquals(2, ((JSONArray)body.get("subscribers")).length());
        assertEquals(json1, ((JSONArray)body.get("subscribers")).get(0));
        assertEquals(json2, ((JSONArray)body.get("subscribers")).get(1));
    }
}