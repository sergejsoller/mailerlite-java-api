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

import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;
import work.soller.ml.action.AbstractRestAction;
import work.soller.ml.model.Activity;
import work.soller.ml.model.Subscriber;

import static org.junit.Assert.assertEquals;

public class GetSubscriberActivitiesActionTest extends AbstractActionTest {
    private GetSubscriberActivitiesAction action;

    @Test
    public void testPathAndVerbWithoutType() {
        this.action = new GetSubscriberActivitiesAction(context, new Subscriber.Id("123"));
        assertEquals("/subscribers/123/activity", action.getPath());
        assertEquals(AbstractRestAction.Verb.GET, action.getVerb());
    }

    @Test
    public void testPathAndVerbWithType() {
        this.action = new GetSubscriberActivitiesAction(context, new Subscriber.Id("123"), Activity.Type.JUNK);
        assertEquals("/subscribers/123/activity/junks", action.getPath());
        assertEquals(AbstractRestAction.Verb.GET, action.getVerb());
    }
}