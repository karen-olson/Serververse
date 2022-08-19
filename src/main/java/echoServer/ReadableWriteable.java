package echoServer;

import java.io.IOException;

public interface ReadableWriteable extends AutoCloseable {
    String readLine() throws IOException;

    void writeLine(String message) throws IOException;
}