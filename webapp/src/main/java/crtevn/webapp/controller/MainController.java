package crtevn.webapp.controller;

import crtevn.webserver.http.HttpRequestMessage;
import crtevn.webserver.http.application.Route;
import crtevn.webserver.http.application.View;
import crtevn.webserver.http.components.HttpMethod;

public class MainController {

    @Route(path = "/home", method = HttpMethod.GET)
    public View home(HttpRequestMessage httpRequestMessage) {

        return new View("/index.html");
    }

}
