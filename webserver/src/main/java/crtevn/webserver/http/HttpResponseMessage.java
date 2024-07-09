package crtevn.webserver.http;

import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.ResponseBody;
import crtevn.webserver.http.components.StatusLine;
import java.nio.charset.StandardCharsets;

public class HttpResponseMessage {

    private final StatusLine statusLine;
    private final HeaderFields headerFields;
    private final ResponseBody responseBody;

    public HttpResponseMessage(StatusLine statusLine, HeaderFields headerFields,
        ResponseBody responseBody) {
        this.statusLine = statusLine;
        this.headerFields = headerFields;
        this.responseBody = responseBody;
    }

    public String getStatusLine() {
        return statusLine.toString();
    }

    public String getHeaderFields() {
        return headerFields.toString();
    }

    public byte[] getMessageBody() {
        return responseBody.getMessageBody();
    }

    @Override
    public String toString() {
        if (responseBody.isEmpty()) {
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
