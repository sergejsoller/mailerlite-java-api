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

package work.soller.ml.endpoint;

import work.soller.ml.action.field.CreateFieldAction;
import work.soller.ml.action.field.DeleteFieldAction;
import work.soller.ml.action.field.ListFieldsAction;
import work.soller.ml.action.field.UpdateFieldAction;
import work.soller.ml.model.Field;

public interface FieldsEndpoint {

    /**
     * Returns an action builder to request fields.
     *
     * @return A new instance of the action builder
     */
    ListFieldsAction list();

    /**
     * Returns an action builder to create the given field.
     *
     * @param field The fiel to create
     * @return The action builder.
     */
    CreateFieldAction create(Field field);

    /**
     * Returns an action builder to update an existing field.
     *
     * Field must provide a valid id.
     *
     * @param field The field to update
     * @return The action builder
     */
    UpdateFieldAction update(Field field);

    /**
     * Returns an action builder to delete a particular field.
     *
     * @param fieldId The id of the field to delete
     * @return The action builder
     */
    DeleteFieldAction delete(Field.Id fieldId);
}
