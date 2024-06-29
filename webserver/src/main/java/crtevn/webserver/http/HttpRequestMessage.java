package crtevn.webserver.http;

import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.HttpMethod;
import crtevn.webserver.http.components.RequestLine;

public class HttpRequestMessage {

    private final RequestLine requestLine;
    private final HeaderFields headerFields;

    public HttpRequestMessage(RequestLine requestLine, HeaderFields headerFields) {
        this.requestLine = requestLine;
        this.headerFields = headerFields;
    }

    public HttpMethod getHttpMethod() {
        return HttpMethod.from(requestLine.getMethod());
    }

    public String getRequestTarget() {
        return requestLine.getRequestTarget();
    }

    public String getRequestTargetWithoutQueryString() {
        return requestLine.getRequestTargetWithoutQueryString();
    }

    public String[] getQueryParameter(String name) {
        return requestLine.getQueryParam(name);
    }

    public String getHttpVersion() {
        return requestLine.getHttpVersion();
    }

    @Override
    public String toString() {
        return requestLine.toString()
            + headerFields.toString();
    }
}
