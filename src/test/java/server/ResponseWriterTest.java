package server;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseWriterTest {
    @Test
    void itWritesA200ResponseWithOneHeaderAndNoBody() {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response(
                "HTTP/1.1",
                "200 OK",
                Map.of("Content-Length", "0"),
                ""
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n"
        ), testReaderWriter.received());
    }

    @Test
    void itWritesA200ResponseWithABody() {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response(
                "HTTP/1.1",
                "200 OK",
                Map.of("Content-Length", "11"),
                "Hello world"
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of("HTTP/1.1 200 OK\r\nContent-Length: 11\r\n\r\nHello world"), testReaderWriter.received());
    }

    @Test
    void itWritesA200ResponseWithMultipleHeadersAndABody() {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Length", "11");
        headers.put("Content-Type", "text");

        Response response = new Response(
                "HTTP/1.1",
                "200 OK",
                headers,
                "Hello world"
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of("HTTP/1.1 200 OK\r\nContent-Length: 11\r\nContent-Type: text\r\n\r\nHello world"), testReaderWriter.received());
    }

    @Test
    void itWritesA404Response() {
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        Response response = new Response(
                "HTTP/1.1",
                "404 Not Found",
                Map.of("Content-Length", "0"),
                ""
        );

        new ResponseWriter()
                .call(testReaderWriter, response);

        assertEquals(List.of(
                "HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n"
        ), testReaderWriter.received());
    }
}
