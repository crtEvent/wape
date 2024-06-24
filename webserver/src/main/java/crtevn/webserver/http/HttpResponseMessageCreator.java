package crtevn.webserver.http;

import crtevn.webserver.Config;
import crtevn.webserver.http.application.View;
import crtevn.webserver.http.application.RouteMapper;
import crtevn.webserver.http.application.RouteValue;
import crtevn.webserver.http.components.HttpMethod;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpResponseMessageCreator {

    private static final RouteMapper routeMapper = RouteMapper.getInstance();

    public static HttpResponseMessage create(HttpRequestMessage httpRequestMessage)
        throws IOException
    {
        HttpMethod httpMethod = httpRequestMessage.getHttpMethod();
        String requestTarget = httpRequestMessage.getRequestTargetWithoutQueryString();

        if (routeMapper.match(httpMethod, requestTarget)) {
            RouteValue routeValue = routeMapper.get(httpMethod, requestTarget);
            View view = routeValue.runMethod(httpRequestMessage);

            return buildMessage(view.resourcePath());
        } else {
            return buildMessage(requestTarget);
        }
    }

    private static HttpResponseMessage buildMessage(String viewPath) throws IOException {
        StringBuilder headerFields = new StringBuilder();

        Path path = Paths.get(Config.getInstance().getStaticResourcesPath() + viewPath);
        byte[] messageBody = Files.readAllBytes(path);

        headerFields
            .append("Content-Type: text/html;charset=utf-8").append(System.lineSeparator())
            .append("Content-Length: ").append(messageBody.length).append(System.lineSeparator());

        return new HttpResponseMessage("HTTP/1.1 200 OK\r\n", headerFields.toString(), messageBody);
    }
}
