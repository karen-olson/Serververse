package server;

import java.io.IOException;

public interface ReadableWriteable extends AutoCloseable {
    String read(int contentLength) throws IOException;

    String readLine() throws IOException;

    void writeLine(String message);
}