package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

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
}
