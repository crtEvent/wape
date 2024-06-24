package crtevn.webserver.http.components;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

public class QueryString {

    private final Map<String, String> queryParams = new LinkedHashMap<>();

    public QueryString(String queryString) {
        parseQueryString(queryString);
    }

    private void parseQueryString(String queryString) {
        if (queryString == null || queryString.isEmpty())
            return;

        String[] params = URLDecoder.decode(queryString, StandardCharsets.UTF_8).split("&");
        for (String param : params) {
            String[] keyAndValue = param.split("=");
            String key = keyAndValue[0];
            String value = keyAndValue.length == 2 ? keyAndValue[1] : "";

            queryParams.merge(key, value, (oldValue, newValue)
                -> newValue.isEmpty() ? oldValue : oldValue + "," + newValue);
        }
    }

    public String[] getQueryParam(String parameterName) {
        String paramValue = queryParams.get(parameterName);
        return paramValue == null
            ? new String[]{""}
            : paramValue.split(",");
    }

    @Override
    public String toString() {
        if (queryParams.isEmpty()) {
            return "";
        }

        StringJoiner joiner = new StringJoiner("&", "?", "");
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            joiner.add(entry.getKey() + "=" + entry.getValue());
        }
        return joiner.toString();
    }
}
