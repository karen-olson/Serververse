package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestLineParserTest {

    @Test
    void itParsesAGetMethod() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET / HTTP/1.1\r\n");

        RequestLineParser.RequestLine requestLine = new RequestLineParser()
                .parse(readableWriteable);

        assertEquals("GET", requestLine.method());
    }

    @Test
    void itParsesAPostMethod() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("POST / HTTP/1.1\r\n");

        RequestLineParser.RequestLine requestLine = new RequestLineParser()
                .parse(readableWriteable);

        assertEquals("POST", requestLine.method());
    }

    @Test
    void itParsesTheRootPath() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET / HTTP/1.1\r\n");

        RequestLineParser.RequestLine requestLine = new RequestLineParser()
                .parse(readableWriteable);

        assertEquals("/", requestLine.path());
    }

    @Test
    void itParsesAFullPathToAResource() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET /path/to/resource HTTP/1.1\r\n");

        RequestLineParser.RequestLine requestLine = new RequestLineParser()
                .parse(readableWriteable);

        assertEquals("/path/to/resource", requestLine.path());
    }
}
