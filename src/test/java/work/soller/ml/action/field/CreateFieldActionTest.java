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

package work.soller.ml.action.field;

import org.json.JSONObject;
import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;
import work.soller.ml.action.RestAction;
import work.soller.ml.model.Field;

import static org.junit.Assert.assertEquals;

public class CreateFieldActionTest extends AbstractActionTest {

    @Test
    public void testPathAndVerb() {
        // When
        CreateFieldAction action = new CreateFieldAction(context, new Field());

        // Then
        assertPathAndVerb(action, "/fields", RestAction.Verb.POST);
    }

    @Test
    public void testBody() {
        // When
        JSONObject jsonObject = new JSONObject();
        CreateFieldAction action = new CreateFieldAction(context, new Field(jsonObject));

        // Then
        assertEquals(jsonObject, action.getBody());
    }
}