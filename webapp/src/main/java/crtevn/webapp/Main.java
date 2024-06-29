package crtevn.webapp;

import crtevn.webapp.controller.MainController;
import crtevn.webapp.controller.MemberController;
import crtevn.webapp.model.Member;
import crtevn.webapp.repository.MemberRepository;
import crtevn.webserver.WebServer;

public class Main {

    public static void main(String[] args) {
        initializeComponent();

        String rootPath = System.getProperty("user.dir");

        WebServer webServer = new WebServer(8081, rootPath)
            .addRouter(MainController.class)
            .addRouter(MemberController.class);

        webServer.start();
    }

    public static void initializeComponent() {
        MemberRepository memberRepository = MemberRepository.initialize();
        memberRepository.save(new Member("admin@wape.com", "qwer1234!"));
    }

}
