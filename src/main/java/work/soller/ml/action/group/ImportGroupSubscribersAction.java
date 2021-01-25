/*
 * Copyright (c) 2021 Sergej Soller (sergej@soller.work)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package work.soller.ml.action.group;

import org.json.JSONArray;
import org.json.JSONObject;
import work.soller.ml.action.ObjectRestAction;
import work.soller.ml.action.RestActionContext;
import work.soller.ml.model.Group;
import work.soller.ml.model.ImportResult;
import work.soller.ml.model.Subscriber;

import java.util.Collection;

public class ImportGroupSubscribersAction extends ObjectRestAction<ImportResult> {
    public static final String RESUBSCRIBE = "resubscribe";
    public static final String AUTORESPONDERS = "autoresponders";
    public static final String RETURN_STATUS = "return_status";
    public static final String SUBSCRIBERS = "subscribers";

    public ImportGroupSubscribersAction(RestActionContext context, Group.Id groupId, Collection<Subscriber> subscribers) {
        super(context, "/groups/" + getId(groupId) + "/subscribers/import", Verb.POST, toJsonObject(subscribers));
    }

    @Override
    protected ImportResult fromObject(JSONObject jsonObject) {
        return new ImportResult(jsonObject);
    }

    /**
     * @param resubscribe reactivate subscriber if value is true
     */
    public ImportGroupSubscribersAction resubscribe(Boolean resubscribe) {
        getParams().put(RESUBSCRIBE, resubscribe);
        return this;
    }

    public Boolean isResubscribe() {
        return getParam(RESUBSCRIBE, Boolean.class);
    }

    /**
     * @param autoresponders will be sent if value is true
     */
    public ImportGroupSubscribersAction autoresponder(Boolean autoresponders) {
        getParams().put(AUTORESPONDERS, autoresponders);
        return this;
    }

    public Boolean isAutoresponder() {
        return getParam(AUTORESPONDERS, Boolean.class);
    }

    /**
     * @param returnStatus run import in the background
     */
    public ImportGroupSubscribersAction returnStatus(Boolean returnStatus) {
        getParams().put(RETURN_STATUS, returnStatus);
        return this;
    }

    public Boolean isReturnStatus() {
        return getParam(RETURN_STATUS, Boolean.class);
    }

    private static JSONObject toJsonObject(Collection<Subscriber> subscribers) {
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        subscribers.stream()
                .map(Subscriber::getJSONObject)
                .forEach(array::put);
        result.put(SUBSCRIBERS, array);
        return result;
    }
}
