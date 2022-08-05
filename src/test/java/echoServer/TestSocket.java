package echoServer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class TestSocket extends Socket {

    private final String inputString;
    private ByteArrayOutputStream outputStream;

    public TestSocket(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(inputString.getBytes());
    }

    @Override
    public OutputStream getOutputStream() {
        outputStream = new ByteArrayOutputStream();
        return outputStream;
    }

    public String output() {
        return outputStream.toString();
    }
}
