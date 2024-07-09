package crtevn.webserver.http.components;

public class ResponseBody {

    private final byte[] messageBody;

    public ResponseBody(byte[] messageBody) {
        this.messageBody = messageBody;
    }

    public byte[] getMessageBody() {
        return messageBody;
    }

    public int getLength() {
        return messageBody.length;
    }

    public boolean isEmpty() {
        return messageBody.length == 0;
    }
}
