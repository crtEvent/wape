package crtevn.webserver.http.application;

import crtevn.webserver.http.HttpRequestMessage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RouteValue{
    private final Class<?> clazz;
    private final Method method;

    public RouteValue(Class<?> clazz, Method method) {
        this.clazz = clazz;
        this.method = method;
    }

    public View runMethod(HttpRequestMessage httpRequestMessage) {
        try {
            return (View) method.invoke(clazz.getDeclaredConstructor().newInstance(), httpRequestMessage);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
