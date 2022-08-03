package echoServer;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

    private class TestSocket extends Socket {

        private final String inputString;
        private ByteArrayOutputStream outputStream;

        public TestSocket(String inputString) {
            this.inputString = inputString;
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            outputStream = new ByteArrayOutputStream();
            return outputStream;
        }

        public String output() {
            return outputStream.toString();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(inputString.getBytes());
        }
    }
}
