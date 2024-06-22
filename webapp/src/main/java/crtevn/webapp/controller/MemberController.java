package crtevn.webapp.controller;

import crtevn.webserver.http.application.View;
import crtevn.webserver.http.application.Route;
import crtevn.webserver.http.components.HttpMethod;

public class MemberController {

    @Route(path = "/login", method = HttpMethod.GET)
    public View login() {

        return new View("/sign-in.html");
    }

}
