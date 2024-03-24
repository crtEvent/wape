package crtevn.webserver.http.components;

public class RequestLine {

    private final String method;
    private final String requestTarget;
    private final String httpVersion;

    public RequestLine(String method, String requestTarget, String httpVersion) {
        this.method = method;
        this.requestTarget = requestTarget;
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    @Override
    public String toString() {
        return method + " " + requestTarget + " " + httpVersion + System.lineSeparator();
    }
}
