package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTest {

    @Test
    void itReturns404NotFoundForNonexistentResource() throws IOException {
        String testRequest = "GET /nonexistent_resource HTTP/1.1\r\nContent-Length:0\r\n";
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send(testRequest);

        new HTTPServer().call(testReaderWriter);

        assertEquals(List.of("HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n"),
                testReaderWriter.received());
    }

    public static class TestReaderWriter implements ReadableWriteable {

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

        public TestReaderWriter send(String message) {
            toRead.add(message);
            return this;
        }

        public List<String> received() {
            return written;
        }

        @Override
        public void close() {

        }
    }
}