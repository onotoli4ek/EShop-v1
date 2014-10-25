package custom_view_framework;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomHttpSession {
    private final Map<String, Object> impl = new ConcurrentHashMap<>();
    public void putAttribute (String key, Object value){
        impl.put(key, value);
    }
    public Object getAttribute (String key){
        return impl.get(key);
    }
    public Iterator<String> getAttributeNames () {
        return impl.keySet().iterator();
    }
}
