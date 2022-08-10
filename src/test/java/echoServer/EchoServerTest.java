package echoServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EchoServerTest {
    @Test
    public void itEchoesSentText() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter().send("hello\n");
        TestListener testListener = new TestListener(testReaderWriter);
        new EchoServer(testListener).serve();

        assertTrue(testReaderWriter.received("hello\n"));
    }

    private static class TestReaderWriter implements ReadableWriteable {

        private final List<String> toRead = new ArrayList<>();
        private final List<String> written = new ArrayList<>();

        public TestReaderWriter send(String message) {
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

    private record TestListener(TestReaderWriter testReaderWriter) implements PortListenable {

        @Override
        public ReadableWriteable listen() {
            return this.testReaderWriter;
        }
    }
}