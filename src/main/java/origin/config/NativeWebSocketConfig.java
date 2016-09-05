package origin.config;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Duskrain on 2016/9/5.
 */
public class NativeWebSocketConfig implements ServerApplicationConfig {


    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> scanned) {

        return null;
    }

    // 注解方式
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
        System.out.println("Scann websocket...");
        Set<Class<?>> results = new HashSet<>();
        for(Class<?> clazz : scanned){
            results.add(clazz);
        }
        System.out.println("Scanned " + results.size());
        return results;
    }
}