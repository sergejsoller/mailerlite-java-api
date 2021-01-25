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

package work.soller.ml.model;

import org.json.JSONObject;
import org.junit.Test;
import work.soller.ml.action.AbstractActionTest;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class FieldTest extends AbstractActionTest {

    @Test
    public void testWrapper() {
        // Given
        JSONObject json = new JSONObject();
        json.put("id", 28349);
        json.put("title", "Favourite");
        json.put("key", "favourite_color");
        json.put("type", "TEXT");
        json.put("date_updated", "2016-03-30 21:08:59");
        json.put("date_created", "2016-03-30 10:08:59");

        // When
        Field field = new Field(json);

        // Then
        assertEquals("28349", field.getId().getValue());
        assertEquals("Favourite", field.getTitle());
        assertEquals("favourite_color", field.getKey());
        assertEquals(Field.Type.TEXT, field.getType());
        assertEquals(LocalDateTime.of(2016, 3, 30, 21, 8, 59), field.getDateUpdated());
        assertEquals(LocalDateTime.of(2016, 3, 30, 10, 8, 59), field.getDateCreated());
    }
}