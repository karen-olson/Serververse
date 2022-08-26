package echoServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServerTest {

    @Test
    void itEchoesSentText() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter().send("hello\n");

        new EchoServer().call(testReaderWriter);

        assertEquals(List.of("hello\n"), testReaderWriter.received());
    }

    @Test
    void itEchoesMultipleLinesOfSentText() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("first line\n")
                .send("second line\n");

        new EchoServer().call(testReaderWriter);

        assertEquals(List.of(
                        "first line\n",
                        "second line\n"),
                testReaderWriter.received());
    }

    private static class TestReaderWriter implements ReadableWriteable {

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
}