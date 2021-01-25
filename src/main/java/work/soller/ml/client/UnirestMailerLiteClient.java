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
