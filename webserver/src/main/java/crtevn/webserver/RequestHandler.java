package crtevn.webserver;

import crtevn.webserver.http.HttpRequestMessage;
import crtevn.webserver.http.HttpRequestMessageCreator;
import crtevn.webserver.http.HttpResponseMessage;
import crtevn.webserver.http.HttpResponseMessageCreator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private final Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    @Override
    public void run() {
        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequestMessage httpRequestMessage = HttpRequestMessageCreator.createFromInputStream(in);

            HttpResponseMessage httpResponseMessage = HttpResponseMessageCreator.create(httpRequestMessage);

            ResponseHandler responseHandler = new ResponseHandler(out, httpResponseMessage);
            responseHandler.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
