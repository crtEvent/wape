package crtevn.webserver.http.application;

public class View {

    private static final String REDIRECT_DELIMITER = "redirect:";

    private final String resourcePath;
    private final boolean redirect;

    public View(String resourcePath) {
        String[] split = resourcePath.split(REDIRECT_DELIMITER);

        if(split.length == 2) {
            this.redirect = true;
            this.resourcePath = split[1];
        } else {
            this.redirect = false;
            this.resourcePath = resourcePath;
        }
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
