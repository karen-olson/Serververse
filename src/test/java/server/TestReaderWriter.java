package server;

import java.util.ArrayList;
import java.util.List;

public class TestReaderWriter implements ReadableWriteable {

    private final List<String> toRead = new ArrayList<>();
    private final List<String> written = new ArrayList<>();

    @Override
    public String readLine() {
        if (toRead.isEmpty()) {
            return null;
        }
        return toRead.remove(0);
    }

    @Override
    public String read(int contentLength) {
        String body = "";
        while (body.length() < contentLength) {
            body += this.readLine();
        }
        return body;
    }

    @Override
    public void writeLine(String message) {
        written.add(message);
    }

    @Override
    public void close() {

    }

    public TestReaderWriter send(String message) {
        toRead.add(message);
        return this;
    }

    public List<String> received() {
        return written;
    }
}