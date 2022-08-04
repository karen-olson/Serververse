package echoServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocketConnectionTest {

    @Test
    void readsALineOfText() throws IOException {
        Connection connection = new SocketConnection(new TestSocket("Hello\n"));

        String message = connection.readLine();

        assertEquals("Hello", message);
    }

    @Test
    void writesALineOfText() throws IOException {
        TestSocket testSocket = new TestSocket("");
        Connection connection = new SocketConnection(testSocket);

        connection.writeLine("Hello");

        String output = testSocket.output();

        assertEquals("Hello\n", output);
    }

}
