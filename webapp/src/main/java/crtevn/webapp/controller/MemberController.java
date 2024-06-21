package crtevn.webapp.controller;

import crtevn.webserver.http.application.Route;
import crtevn.webserver.http.components.HttpMethod;

public class MemberController {

    @Route(path = "/login", method = HttpMethod.GET)
    public void login() {
        System.out.println("dd");
    }

}
