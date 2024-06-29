package crtevn.webserver.http.components;

public class MessageBody {

    private final byte[] messageBody;

    public MessageBody(byte[] messageBody) {
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
