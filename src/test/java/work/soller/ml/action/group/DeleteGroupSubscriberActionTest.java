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

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;
import work.soller.ml.action.RestAction;
import work.soller.ml.model.Group;
import work.soller.ml.model.Subscriber;

import static org.junit.Assert.assertEquals;

public class DeleteGroupSubscriberActionTest extends AbstractActionTest {
    private DeleteGroupSubscriberAction action;

    @Before
    public void setup() {
        action = new DeleteGroupSubscriberAction(context, new Group.Id("123"), new Subscriber.Id("456"));
    }

    @Test
    public void testPathAndVerb() {
        // Then
        assertPathAndVerb(action, "/groups/123/subscribers/456", RestAction.Verb.DELETE);
    }

    @Test
    public void testSuccess() {
        // When
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);

        // Then
        assertEquals(true, action.fromObject(jsonObject));
    }
}