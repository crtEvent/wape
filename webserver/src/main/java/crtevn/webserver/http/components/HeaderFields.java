package crtevn.webserver.http.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HeaderFields {

    private static final String CONTENT_LENGTH = "Content-Length";

    /**
     * In the map, the key is the field-name, and the value is the field-value.
     */
    private final Map<String, String> headerFields = new LinkedHashMap<>();

    public void put(String fieldName, String fieldValue) {
        headerFields.put(fieldName, fieldValue);
    }

    public String get(String fieldName) {
        String fieldValue = this.headerFields.get(fieldName);
        return fieldValue != null ? fieldValue : "";
    }

    public long getContentLength() {
        String contentLength = this.headerFields.get(CONTENT_LENGTH);

        try {
            return Long.parseLong(contentLength);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Entry<String, String> entry : headerFields.entrySet()) {
            builder
                .append(entry.getKey()).append(": ")
                .append(entry.getValue())
                .append(System.lineSeparator());
        }

        return builder.toString();
    }
}
