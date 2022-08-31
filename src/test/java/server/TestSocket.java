package server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class TestSocket extends Socket {

    private final String inputString;
    private final List<String> events = new ArrayList<>();
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    public TestSocket(String inputString) {
        this.inputString = inputString;
    }

    @Override
    public InputStream getInputStream() {
        this.events.add("A new reader accessed the input stream");
        inputStream = new ByteArrayInputStream(inputString.getBytes());
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        events.add("A new writer accessed the output stream");
        outputStream = new ByteArrayOutputStream();
        return outputStream;
    }

    public String output() {
        return outputStream.toString();
    }

    public void close() {
        events.add("The socket has been closed");
    }

    public List<String> events() {
        return events;
    }
}
