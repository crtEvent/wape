package crtevn.webserver.http.components;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class ParameterParser {

    private final Map<String, String> parameters = new LinkedHashMap<>();

    public ParameterParser(String parameters) {
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) {
        if (parameters == null || parameters.isEmpty())
            return;

        String[] params = URLDecoder.decode(parameters, StandardCharsets.UTF_8).split("&");
        for (String param : params) {
            String[] keyAndValue = param.split("=");
            String key = keyAndValue[0];
            String value = keyAndValue.length == 2 ? keyAndValue[1] : "";

            this.parameters.merge(key, value, (oldValue, newValue)
                -> newValue.isEmpty() ? oldValue : oldValue + "," + newValue);
        }
    }

    public String[] getParameter(String parameterName) {
        String paramValue = parameters.get(parameterName);
        return paramValue == null
            ? new String[]{""}
            : paramValue.split(",");
    }

    @Override
    public String toString() {
        if (parameters.isEmpty()) {
            return "";
        }

        StringJoiner joiner = new StringJoiner("&", "", "");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            joiner.add(entry.getKey() + "=" + entry.getValue());
        }
        return joiner.toString() + System.lineSeparator();
    }
}
