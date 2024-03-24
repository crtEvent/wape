package crtevn.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private final Config config;

    public WebServer(int port, String staticResourcesPath) {
        config = Config.initialize(port, staticResourcesPath);
    }

    public void start() {
        try (ServerSocket listenSocket = new ServerSocket(config.getPort())) {

            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                Thread thread = new Thread(new RequestHandler(connection));
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
