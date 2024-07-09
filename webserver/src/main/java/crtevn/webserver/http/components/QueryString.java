package crtevn.webserver.http.components;

public class QueryString {

    private final ParameterParser parameterParser;

    public QueryString(String queryString) {
        this.parameterParser = new ParameterParser(queryString);
    }

    public String[] getQueryParam(String parameterName) {
        return parameterParser.getParameter(parameterName);
    }

    @Override
    public String toString() {
        return parameterParser.toString();
    }
}
