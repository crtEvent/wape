package crtevn.webserver.http.application;

import crtevn.webserver.http.components.HttpMethod;

public record RouteKey(
    HttpMethod httpMethod,
    String resourcePath
) {

}
