package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    @Test
    void itWritesAFormattedResponseWithNoBody() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response("HTTP/1.1", "200 OK", "Content-Length:0", "");

        response.write(testReaderWriter);

        assertEquals(List.of("HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n"), testReaderWriter.received());
    }

    @Test
    void itWritesAFormattedResponseWithABody() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response("HTTP/1.1", "200 OK", "Content-Length:11", "Hello world");

        response.write(testReaderWriter);

        assertEquals(List.of("HTTP/1.1 200 OK\r\nContent-Length:11\r\n\nHello world"), testReaderWriter.received());
    }
    
}
