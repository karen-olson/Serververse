package server;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFoundHandlerTest {

    @Test
    void itReturnsANotFoundResponse() {
        Request request = new Request(
                "GET",
                "/path/to/nonexistent_resource",
                Map.of("Content-Length", "0",
                        "Content-Type", "text"
                )
        );

        Response response = new NotFoundHandler()
                .call(request);

        Response expectedResponse = new Response(
                "HTTP/1.1",
                "404 Not Found",
                "Content-Length:0",
                ""
        );

        assertEquals(expectedResponse, response);
    }
}