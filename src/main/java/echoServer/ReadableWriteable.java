package echoServer;

import java.io.IOException;

public interface ReadableWriteable {
    String readLine() throws IOException;

    void writeLine(String message) throws IOException;

    void close() throws IOException;
}