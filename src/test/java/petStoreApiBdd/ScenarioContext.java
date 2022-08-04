package petStoreApiBdd;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final Map<String, Object> data = new HashMap<>();

    public void save(String key, Object value) {
        data.put(key, value);
    }

    public <T> T getData(String key) {
        return (T) data.get(key);
    }
}
