package crtevn.webserver.http.components;

public enum HttpMethod {
    GET;

    public static HttpMethod from(String method) {
        for (HttpMethod element : HttpMethod.values()) {
            if (element.name().equalsIgnoreCase(method)) {
                return element;
            }
        }
        throw new IllegalArgumentException("No enum constant for method name: " + method);
    }
}
