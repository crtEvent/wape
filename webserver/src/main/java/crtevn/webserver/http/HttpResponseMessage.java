package crtevn.webserver.http;

import java.nio.charset.StandardCharsets;

public class HttpResponseMessage {

    private final String statusLine;
    private final String headerFields;
    private final byte[] messageBody;

    public HttpResponseMessage(String statusLine, String headerFields, byte[] messageBody) {
        this.statusLine = statusLine;
        this.headerFields = headerFields;
        this.messageBody = messageBody;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public String getHeaderFields() {
        return headerFields;
    }

    public byte[] getMessageBody() {
        return messageBody;
    }

    @Override
    public String toString() {
        return statusLine
            + headerFields
            + System.lineSeparator()
            + new String(messageBody, StandardCharsets.UTF_8);
    }
}
