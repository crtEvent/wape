package crtevn.webserver.http;

import crtevn.webserver.http.components.HeaderFields;
import crtevn.webserver.http.components.RequestLine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestMessageCreator {

    private static final int START_LINE_COMPONENTS_NUMBER = 3;
    private static final int HEADER_FIELD_COMPONENTS_NUMBER = 2;

    public static HttpRequestMessage createFromInputStream(InputStream in) throws IOException {
        List<String> lineByLine = readLinesFromInputStream(in);

        RequestLine requestLine = createRequestLine(lineByLine.get(0));
        HeaderFields headerFields = createHeaderFields(lineByLine.subList(1, lineByLine.size()));

        return new HttpRequestMessage(requestLine, headerFields);
    }

    private static List<String> readLinesFromInputStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        List<String> lineByLine = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            lineByLine.add(line);
        }

        return lineByLine;
    }

    /**
     * request-line   = method SP request-target SP HTTP-version CRLF
     */
    private static RequestLine createRequestLine(String startLine) {
        String[] components = startLine.split(" ");

        if (components.length != START_LINE_COMPONENTS_NUMBER) {
            throw new IllegalArgumentException(
                "The number of start line components does not match.");
        }

        return new RequestLine(components[0], components[1], components[2]);
    }

    private static HeaderFields createHeaderFields(List<String> headerFields) {
        Map<String, String> map = new LinkedHashMap<>();
        for (String headerField : headerFields) {
            String[] components = headerField.split(":\\s?", 2);
            if (components.length != HEADER_FIELD_COMPONENTS_NUMBER) {
                throw new IllegalArgumentException("The number of header field components does not match.");
            }

            map.put(components[0], components[1].trim());
        }

        return new HeaderFields(map);
    }

}
