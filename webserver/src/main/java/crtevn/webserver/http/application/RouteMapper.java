package crtevn.webserver.http.application;

import crtevn.webserver.Config;
import crtevn.webserver.http.components.HttpMethod;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RouteMapper {

    private static RouteMapper instance;
    private final Map<RouteKey, RouteValue> routeMap;

    private RouteMapper() {
        routeMap = new ConcurrentHashMap<>();
    }

    public static synchronized RouteMapper initialize() {
        if (instance == null) {
            instance = new RouteMapper();
        } else {
            throw new IllegalStateException("RouteMapper is already initialized.");
        }
        return instance;
    }

    public static RouteMapper getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                "RouteMapper is not initialized yet. Please call initialize() first.");
        }
        return instance;
    }

    public void add(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Route.class)) {
                Route route = method.getAnnotation(Route.class);
                RouteKey routeKey = new RouteKey(route.method(), route.path());

                if (routeMap.containsKey(routeKey)) {
                    throw new IllegalArgumentException("Duplicate Route method and path.: " + routeKey);
                }

                routeMap.put(new RouteKey(route.method(), route.path()), new RouteValue(clazz, method));
            }
        }
    }

    public RouteValue get(HttpMethod method, String requestTarget) {
        return routeMap.get(new RouteKey(method, requestTarget));
    }

    public boolean match(HttpMethod method, String requestTarget) {
        return routeMap.containsKey(new RouteKey(method, requestTarget));
    }
}
