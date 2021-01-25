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

package work.soller.ml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import work.soller.ml.action.RestActionException;
import work.soller.ml.client.MailerLiteClient;
import work.soller.ml.client.MailerLiteCredentials;
import work.soller.ml.client.UnirestMailerLiteClient;
import work.soller.ml.model.Subscriber;

import java.util.List;

public class StartHere {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartHere.class);

    public static void main(String[] args) throws RestActionException {
        // Set ML credentials
        String apiKey = System.getenv("ENV_API_KEY");
        MailerLiteCredentials credentials = new MailerLiteCredentials(apiKey);

        // Create a new unirest mailer lite client
        MailerLiteClient client = new UnirestMailerLiteClient(credentials);

        // Create a new subscriber
        List<Subscriber> searchResult = client.subscribers()
                .find()
                .query("foo@some-domain.com")
                .run();

        // Get first result
        Subscriber subscriber = !searchResult.isEmpty() ? searchResult.get(0) : null;

        // Update subscriber field
        if (subscriber != null) {
            subscriber.setField("city", "Foo City");
            subscriber = client.subscribers()
                    .update(subscriber)
                    .run();

            LOGGER.info("Subscriber [" + subscriber + "] is living in [" + subscriber.getField("city") + "]");
        }
    }
}
