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
        return HttpMethod.from(requestLine.method());
    }

    public String getRequestTarget() {
        return requestLine.requestTarget();
    }

    @Override
    public String toString() {
        return requestLine.toString()
            + headerFields.toString();
    }
}
