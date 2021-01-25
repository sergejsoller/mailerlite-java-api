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

package work.soller.ml.action;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class ListRestAction<R> extends AbstractRestAction<List<R>> {
    public ListRestAction(RestActionContext context, String path, Verb verb) {
        super(context, path, verb, null);
    }

    @Override
    public List<R> run() throws RestActionException {
        RestActionRunner actionRunner = getContext().getActionRunner();
        JSONArray arrayResult = actionRunner.run(this);
        return fromArray(arrayResult);
    }

    protected List<R> fromArray(JSONArray arrayResult) {
        return StreamSupport.stream(arrayResult.spliterator(), false)
                .map(JSONObject.class::cast)
                .map(this::fromObject)
                .collect(Collectors.toList());
    }

    protected abstract R fromObject(JSONObject jsonObject);
}
