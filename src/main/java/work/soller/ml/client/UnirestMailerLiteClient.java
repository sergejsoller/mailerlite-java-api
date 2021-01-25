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

package work.soller.ml.client;

import work.soller.ml.action.RestActionContext;
import work.soller.ml.action.RestActionRunner;
import work.soller.ml.endpoint.*;

public class UnirestMailerLiteClient implements MailerLiteClient {
    /**
     * The context is used by the rest action to access
     * MailerLite credentials and also an action runner to
     * execute itself
     */
    private final RestActionContext context;

    /**
     * ------------------------------------------------------
     * The endpoints. (Will be created when first time accessed.)
     * ------------------------------------------------------
     */

    private SubscribersEndpoint subscribersEndpoint;
    private GroupsEndpoint groupsEndpoint;
    private FieldsEndpoint fieldsEndpoint;

    public UnirestMailerLiteClient(MailerLiteCredentials context) {
        this(context, new UnirestActionRunner());
    }

    public UnirestMailerLiteClient(MailerLiteCredentials context, RestActionRunner runner) {
        this.context = new RestActionContext(context, runner);
    }

    @Override
    public SubscribersEndpoint subscribers() {
        if (this.subscribersEndpoint == null) {
            this.subscribersEndpoint = new SubscribersRestEndpoint(context);
        }
        return this.subscribersEndpoint;
    }

    @Override
    public GroupsEndpoint groups() {
        if (this.groupsEndpoint == null) {
            this.groupsEndpoint = new GroupsRestEndpoint(context);
        }
        return this.groupsEndpoint;
    }

    @Override
    public FieldsEndpoint fields() {
        if (this.fieldsEndpoint == null) {
            this.fieldsEndpoint = new FieldRestEndpoint(context);
        }
        return fieldsEndpoint;
    }

    @Override
    public RestActionContext getContext() {
        return context;
    }
}
