package crtevn.webserver.http;

import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.HttpMethod;
import crtevn.webserver.http.components.RequestBody;
import crtevn.webserver.http.components.RequestLine;

public class HttpRequestMessage {

    private final RequestLine requestLine;
    private final HeaderFields headerFields;
    private final RequestBody requestBody;

    public HttpRequestMessage(RequestLine requestLine, HeaderFields headerFields,
        RequestBody requestBody
    ) {
        this.requestLine = requestLine;
        this.headerFields = headerFields;
        this.requestBody = requestBody;
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

    public String[] getBodyParameter(String name) {
        return requestBody.getBodyParam(name);
    }

    public String getHttpVersion() {
        return requestLine.getHttpVersion();
    }

    @Override
    public String toString() {
        if (requestBody.isEmpty()) {
            return requestLine.toString()
                + headerFields.toString();
        } else {
            return requestLine.toString()
                + headerFields.toString()
                + System.lineSeparator()
                + requestBody.toString();
        }
    }
}
