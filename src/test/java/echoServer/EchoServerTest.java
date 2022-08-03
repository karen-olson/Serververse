package echoServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EchoServerTest {
    @Test
    public void itEchoesSentText() throws IOException {
        TestConnection connection = new TestConnection().send("hello\n");
        TestListener listener = new TestListener(connection);
        new EchoServer(listener).serve();

        assertTrue(connection.received("hello\n"));
    }

    private class TestConnection implements Connection {

        private final List<String> toRead = new ArrayList<>();
        private final List<String> written = new ArrayList<>();

        public TestConnection send(String message) {
            toRead.add(message);
            return this;
        }

        public boolean received(String message) {
            return written.contains(message);
        }

        public String readLine() {
            return toRead.remove(0);
        }

        public void writeLine(String message) {
            written.add(message);
        }
    }

    private class TestListener implements Listener {
        private final TestConnection connection;

        public TestListener(TestConnection connection) {
            this.connection = connection;
        }

        @Override
        public Connection listen() {
            return this.connection;
        }
    }
}