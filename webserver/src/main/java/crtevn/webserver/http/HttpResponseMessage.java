package crtevn.webserver.http;

import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.MessageBody;
import crtevn.webserver.http.components.StatusLine;
import java.nio.charset.StandardCharsets;

public class HttpResponseMessage {

    private final StatusLine statusLine;
    private final HeaderFields headerFields;
    private final MessageBody messageBody;

    public HttpResponseMessage(StatusLine statusLine, HeaderFields headerFields,
        MessageBody messageBody) {
        this.statusLine = statusLine;
        this.headerFields = headerFields;
        this.messageBody = messageBody;
    }

    public String getStatusLine() {
        return statusLine.toString();
    }

    public String getHeaderFields() {
        return headerFields.toString();
    }

    public byte[] getMessageBody() {
        return messageBody.getMessageBody();
    }

    @Override
    public String toString() {
        if (messageBody.isEmpty()) {
            return getStatusLine()
                + getHeaderFields();
        } else {
            return getStatusLine()
                + getHeaderFields()
                + System.lineSeparator()
                + new String(getMessageBody(), StandardCharsets.UTF_8);
        }

    }
}
