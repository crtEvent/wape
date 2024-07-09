package crtevn.webserver.http.components;

public enum HttpHeaders {
    CONTENT_LENGTH("Content-Length");

    private final String fieldName;

    HttpHeaders(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
