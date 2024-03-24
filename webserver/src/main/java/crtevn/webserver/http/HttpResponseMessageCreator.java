package crtevn.webserver.http;

import crtevn.webserver.Config;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpResponseMessageCreator {

    public static HttpResponseMessage create200ResponseMessage(
        HttpRequestMessage httpRequestMessage)
        throws IOException {
        StringBuilder headerFields = new StringBuilder();

        Path path = Paths.get(
            Config.getInstance().getStaticResourcesPath() + httpRequestMessage.getRequestTarget());
        byte[] messageBody = Files.readAllBytes(path);

        headerFields
            .append("Content-Type: text/html;charset=utf-8").append(System.lineSeparator())
            .append("Content-Length: ").append(messageBody.length).append(System.lineSeparator());

        return new HttpResponseMessage("HTTP/1.1 200 OK\r\n", headerFields.toString(), messageBody);
    }
}
