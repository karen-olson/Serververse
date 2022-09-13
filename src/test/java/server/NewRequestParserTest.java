package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewRequestParserTest {
    @Test
    void itParsesAGetMethod() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET / HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        NewRequestParser.NewRequest request = new NewRequestParser()
                .call(readableWriteable);

        assertEquals("GET", request.method());
    }

    @Test
    void itParsesAPostMethod() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("POST / HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        NewRequestParser.NewRequest request = new NewRequestParser()
                .call(readableWriteable);

        assertEquals("POST", request.method());
    }

    @Test
    void itParsesTheRootPath() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET / HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        NewRequestParser.NewRequest request = new NewRequestParser()
                .call(readableWriteable);

        assertEquals("/", request.path());
    }

    @Test
    void itParsesAFullPathToAResource() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET /path/to/resource HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        NewRequestParser.NewRequest request = new NewRequestParser()
                .call(readableWriteable);

        assertEquals("/path/to/resource", request.path());
    }

    @Test
    void itParsesASingleHeader() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET /path/to/resource HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        NewRequestParser.NewRequest request = new NewRequestParser()
                .call(readableWriteable);

        assertEquals(Map.of("Content-Length", "0"), request.headers());
    }

    @Test
    void itParsesMultipleHeaders() throws IOException {
        UUID apiKey = UUID.randomUUID();
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET /path/to/resource HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n");

        NewRequestParser.NewRequest request = new NewRequestParser()
                .call(readableWriteable);

        assertEquals(Map.of(
                "Content-Length", "0",
                "Content-Type", "text",
                "API-Key", apiKey.toString()
        ), request.headers());
    }
}
