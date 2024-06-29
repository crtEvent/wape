package crtevn.webserver;

import crtevn.webserver.http.application.RouteMapper;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private final Config config;
    private final RouteMapper routeMapper;

    public WebServer(int port, String staticResourcesPath) {
        config = Config.initialize(port, staticResourcesPath);
        routeMapper = RouteMapper.initialize();
    }

    public WebServer addRouter(Class<?> clazz) {
        routeMapper.add(clazz);

        return this;
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
