package server;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplePostHandlerTest {
    @Test
    void itEchoesTheBody() {
        Request request = new Request(
                "POST",
                "/echo_body",
                Map.of("content-length", "11",
                        "content-type", "text/plain"
                ),
                "Hello World"
        );

        Response response = new SimplePostHandler().call(request);
        
        Response expectedResponse = new Response(
                "HTTP/1.1",
                "200 OK",
                Map.of("Content-Length", "11",
                        "Content-Type", "text/plain"
                ),
                "Hello World"
        );

        assertEquals(expectedResponse, response);
    }
}
