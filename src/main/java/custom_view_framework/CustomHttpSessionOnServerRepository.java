package custom_view_framework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//todo: realize old session clearing funcionality
//todo: realize session listener functionality
//todo: rewrite product bucket to this session implementation
public class CustomHttpSessionOnServerRepository {
    private static Map<String, CustomHttpSession> sessions = new ConcurrentHashMap<>();
    public static CustomHttpSession getSession (String sessionId){
        return getSession(sessionId, true);
    }
    public static CustomHttpSession getSession (String sessionId, boolean canCreate){
        CustomHttpSession result = sessions.get(sessionId);
        if (result == null && canCreate){
            result = new CustomHttpSession();
            sessions.put(sessionId, result);
        }
        return  result;
    }
}
