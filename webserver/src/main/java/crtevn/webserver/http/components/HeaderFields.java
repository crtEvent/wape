package crtevn.webserver.http.components;

import java.util.Map;
import java.util.Map.Entry;

public class HeaderFields {

    private static final int HEADER_FIELD_COMPONENTS_NUMBER = 2;
    private static final String CONTENT_LENGTH = "Content-Length";

    /**
     * In the map, the key is the field-name, and the value is the field-value.
     */
    private final Map<String, String> headerFields;

    public HeaderFields(Map<String, String> headerFields) {
        this.headerFields = headerFields;
    }

    public String getFieldValue(String fieldName) {
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
