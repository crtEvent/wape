package crtevn.webserver.http.components;

public class StatusLine {

    private final String httpVersion;
    private final HttpStatus httpStatus;

    public StatusLine(String httpVersion, HttpStatus httpStatus) {
        this.httpVersion = httpVersion;
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return httpVersion + " " + httpStatus.getStatusCode() + " " + httpStatus.getReasonPhrase()
            + System.lineSeparator();
    }
}
