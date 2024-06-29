package crtevn.webserver.http.components;

public class RequestLine {

    private final String method;
    private final String path;
    private final QueryString queryString;
    private final String httpVersion;

    public RequestLine(String method, String requestTarget, String httpVersion) {

        String[] targetParts = requestTarget.split("\\?");

        this.method = method;
        this.path = targetParts[0];
        this.queryString = (targetParts.length == 2)
            ? new QueryString(targetParts[1])
            : new QueryString(null);
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return path + queryString.toString();
    }

    public String getRequestTargetWithoutQueryString() {
        return path;
    }

    public String[] getQueryParam(String name) {
        return queryString.getQueryParam(name);
    }

    public String getHttpVersion() {
        return httpVersion;
    }



    @Override
    public String toString() {
        return method + " " + path + " " + queryString + " " + httpVersion
            + System.lineSeparator();
    }
}
