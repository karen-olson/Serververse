package echoServer;

import java.io.IOException;

public interface Connection {
    String readLine() throws IOException;

    void writeLine(String message) throws IOException;
}