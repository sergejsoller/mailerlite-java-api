package work.soller.ml.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SubscriberTest {

    @Test
    public void convertFieldsArray() {
        // Given
        JSONArray fields = new JSONArray();
        fields.put(new JSONObject(Map.of("key", "name", "value", "foo", "type", "TEXT")));
        fields.put(new JSONObject(Map.of("key", "last_name", "value", "bar", "type", "TEXT")));

        // When
        Subscriber s = new Subscriber(new JSONObject(Map.of("fields", fields)));

        // Then
        assertEquals("foo", s.getField("name"));
        assertEquals("bar", s.getField("last_name"));
    }
}