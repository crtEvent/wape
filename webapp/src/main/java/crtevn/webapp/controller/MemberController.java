package crtevn.webapp.controller;

import crtevn.webapp.model.Member;
import crtevn.webapp.repository.MemberRepository;
import crtevn.webserver.http.HttpRequestMessage;
import crtevn.webserver.http.application.View;
import crtevn.webserver.http.application.Route;
import crtevn.webserver.http.components.HttpMethod;
import java.security.InvalidParameterException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MemberController {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Route(path = "/login", method = HttpMethod.GET)
    public View login(HttpRequestMessage httpRequestMessage) {

        return new View("/sign-in.html");
    }

    @Route(path = "/do-login", method = HttpMethod.GET)
    public View doLogin(HttpRequestMessage httpRequestMessage) {

        String email = httpRequestMessage.getQueryParameter("email")[0];
        String pw = httpRequestMessage.getQueryParameter("password")[0];

        Optional<Member> member = memberRepository.findById(email);

        if (member.isPresent() && member.get().authenticate(email, pw)) {
            return new View("redirect:/home");
        }
        return new View("redirect:/login");
    }

}
