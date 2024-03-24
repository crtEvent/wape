package crtevn.webserver.http;

import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.RequestLine;

public class HttpRequestMessage {

    private final RequestLine requestLine;
    private final HeaderFields headerFields;

    public HttpRequestMessage(RequestLine requestLine, HeaderFields headerFields) {
        this.requestLine = requestLine;
        this.headerFields = headerFields;
    }

    public String getRequestTarget() {
        return requestLine.getRequestTarget();
    }

    @Override
    public String toString() {
        return requestLine.toString()
            + headerFields.toString();
    }
}
