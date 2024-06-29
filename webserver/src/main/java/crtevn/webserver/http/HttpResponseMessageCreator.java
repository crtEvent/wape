package crtevn.webserver.http;

import crtevn.webserver.Config;
import crtevn.webserver.http.application.View;
import crtevn.webserver.http.application.RouteMapper;
import crtevn.webserver.http.application.RouteValue;
import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.HttpMethod;
import crtevn.webserver.http.components.HttpStatus;
import crtevn.webserver.http.components.MessageBody;
import crtevn.webserver.http.components.StatusLine;
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

            StatusLine statusLine;
            MessageBody messageBody;
            HeaderFields headerFields;
            if (view.isRedirect()) {
                statusLine = createStatusLine(httpRequestMessage.getHttpVersion(), HttpStatus.FOUND);
                messageBody = createMessageBody(null);
                headerFields = createDefaultHeaderFields(messageBody);
                headerFields.put("Location", view.getResourcePath());
            } else {
                statusLine = createStatusLine(httpRequestMessage.getHttpVersion(), HttpStatus.OK);
                messageBody = createMessageBody(view.getResourcePath());
                headerFields = createDefaultHeaderFields(messageBody);
            }

            return new HttpResponseMessage(statusLine, headerFields, messageBody);
        } else {
            StatusLine statusLine = createStatusLine(httpRequestMessage.getHttpVersion(), HttpStatus.OK);
            MessageBody messageBody = createMessageBody(requestTarget);
            HeaderFields headerFields = createDefaultHeaderFields(messageBody);

            return new HttpResponseMessage(statusLine, headerFields, messageBody);
        }
    }

    /**
     * status-line = HTTP-version SP status-code SP reason-phrase CRLF
     */
    private static StatusLine createStatusLine(String httpVersion, HttpStatus httpStatus) {

        return new StatusLine(httpVersion, httpStatus);
    }

    /**
     * header-field   = field-name ":" OWS field-value OWS
     *
     * OWS means optional trailing whitespace.
     */
    private static HeaderFields createDefaultHeaderFields(MessageBody messageBody) {
        HeaderFields headerFields = new HeaderFields();
        headerFields.put("Content-Type", "text/html;charset=utf-8");
        headerFields.put("Content-Length", String.valueOf(messageBody.getLength()));

        return headerFields;
    }

    /**
     * message-body = *OCTET
     */
    private static MessageBody createMessageBody(String viewPath) throws IOException {
        if (viewPath == null || viewPath.isEmpty()) {
            return new MessageBody(new byte[]{});
        }

        Path path = Paths.get(Config.getInstance().getStaticResourcesPath() + viewPath);
        return new MessageBody(Files.readAllBytes(path));
    }
}
