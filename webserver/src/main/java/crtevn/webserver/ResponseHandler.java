package crtevn.webserver;

import crtevn.webserver.http.HttpResponseMessage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ResponseHandler {

    private final OutputStream out;
    private final HttpResponseMessage httpResponseMessage;

    public ResponseHandler(OutputStream out, HttpResponseMessage httpResponseMessage) {
        this.out = out;
        this.httpResponseMessage = httpResponseMessage;
    }

    public void flush() throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeBytes(httpResponseMessage.getStatusLine());
        dos.writeBytes(httpResponseMessage.getHeaderFields());
        dos.writeBytes(System.lineSeparator());

        byte[] messageBody = httpResponseMessage.getMessageBody();
        dos.write(messageBody, 0, messageBody.length);

        dos.flush();
    }
}
