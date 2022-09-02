package server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    @Test
    void itConstructsAResponseStringWithNoBody() {
        Response response = new Response("200 OK", "Content-Length:0", "");

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n", response.toString());
    }

    @Test
    void itConstructsAResponseStringWithABody() {
        Response response = new Response("200 OK", "Content-Length:11", "Hello world");

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length:11\r\n\nHello world", response.toString());
    }
}
