package crtevn.webapp;

import crtevn.webserver.WebServer;

public class Main {

    public static void main(String[] args) {
        String staticResourcesPath = System.getProperty("user.dir") + "/webapp/src/main/resources/static";

        WebServer webServer = new WebServer(8080, staticResourcesPath);
        webServer.start();
    }

}
