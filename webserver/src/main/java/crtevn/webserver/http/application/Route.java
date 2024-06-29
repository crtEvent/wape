package crtevn.webserver.http.application;

import crtevn.webserver.http.components.HttpMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Route {
    String path() default "";
    HttpMethod method() default HttpMethod.GET;
}
