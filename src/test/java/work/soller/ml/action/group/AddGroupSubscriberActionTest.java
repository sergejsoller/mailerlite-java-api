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
import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;
import work.soller.ml.action.RestAction;
import work.soller.ml.model.Group;
import work.soller.ml.model.Subscriber;

import static org.junit.Assert.assertEquals;

public class AddGroupSubscriberActionTest extends AbstractActionTest {

    @Test
    public void testPathAndVerb() {
        // When
        AddGroupSubscriberAction action = new AddGroupSubscriberAction(context, new Group.Id("123"), new Subscriber());

        // Then
        assertPathAndVerb(action, "/groups/123/subscribers", RestAction.Verb.POST);
    }

    @Test
    public void testBody() {
        // When
        JSONObject jsonObject = new JSONObject();
        AddGroupSubscriberAction action = new AddGroupSubscriberAction(context, new Group.Id("123"), new Subscriber(jsonObject));

        // Then
        assertEquals(jsonObject, action.getBody());
    }
}