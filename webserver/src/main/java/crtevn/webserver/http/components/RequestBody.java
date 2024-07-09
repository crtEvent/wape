package crtevn.webserver.http.components;

public class RequestBody {

    private final ParameterParser parameterParser;

    public RequestBody(String bodyParameter) {
        this.parameterParser = new ParameterParser(bodyParameter);
    }

    public String[] getBodyParam(String parameterName) {
        return parameterParser.getParameter(parameterName);
    }

    @Override
    public String toString() {
        return parameterParser.toString();
    }

    public int getLength() {
        return parameterParser.toString().length();
    }

    public boolean isEmpty() {
        return getLength() == 0;
    }

}
