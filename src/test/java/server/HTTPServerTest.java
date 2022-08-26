package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTest {

    @Test
    void itReturnsHelloWorld() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();

        new HTTPServer().call(testReaderWriter);

        assertEquals(List.of("HTTP/1.1 200 OK\n\nHello, world"), testReaderWriter.received());
    }

    private class TestReaderWriter implements ReadableWriteable {

        private final List<String> written = new ArrayList<>();

        @Override
        public String readLine() {
            return null;
        }

        @Override
        public void writeLine(String message) {
            written.add(message);
        }

        public List<String> received() {
            return written;
        }

        @Override
        public void close() throws Exception {

        }
    }
}