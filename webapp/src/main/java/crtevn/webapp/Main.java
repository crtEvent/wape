package crtevn.webapp;

import crtevn.webapp.controller.MainController;
import crtevn.webapp.controller.MemberController;
import crtevn.webserver.WebServer;

public class Main {

    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");

        WebServer webServer = new WebServer(8081, rootPath)
            .addRouter(MainController.class)
            .addRouter(MemberController.class);

        webServer.start();
    }

}
