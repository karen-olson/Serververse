package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseWriterTest {
    @Test
    void itWritesA200ResponseWithOneHeaderAndNoBody() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:0",
                ""
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\r\n"
        ), testReaderWriter.received());
    }

    @Test
    void itWritesA200ResponseWithABody() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:11",
                "Hello world"
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of("HTTP/1.1 200 OK\r\nContent-Length:11\r\n\r\nHello world"), testReaderWriter.received());
    }

    @Test
    void itWritesA404Response() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response(
                "HTTP/1.1",
                "404 Not Found",
                "Content-Length:0",
                ""
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of(
                "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\r\n"
        ), testReaderWriter.received());
    }
}
