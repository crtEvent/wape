package crtevn.webserver.http.components;

public record RequestLine(
    String method,
    String requestTarget,
    String httpVersion
) {

    @Override
    public String toString() {
        return method + " " + requestTarget + " " + httpVersion + System.lineSeparator();
    }
}
