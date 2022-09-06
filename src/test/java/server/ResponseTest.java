package server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {

    @Test
    void itHasAllNecessaryFields() {
        Response response = new Response("HTTP/1.1", "200 OK", "Content-Length:11", "Hello world");

        assertEquals("HTTP/1.1", response.getProtocol());
        assertEquals("200 OK", response.getStatusCode());
        assertEquals("Content-Length:11", response.getHeaders());
        assertEquals("Hello world", response.getBody());
    }
}
