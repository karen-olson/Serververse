package server;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    @Test
    void itHandlesGet() {
        Request testRequest = new Request(
                "GET",
                "/",
                Map.of("Content-Length", "0")
        );

        Response response = new Router()
                .call(testRequest);

        Response expectedResponse = new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:0",
                ""
        );
        assertEquals(expectedResponse, response);
    }

    @Test
    void itHandlesSimpleGet() {
        Request testRequest = new Request(
                "GET",
                "/simple_get",
                Map.of("Content-Length", "0")
        );

        Response response = new Router()
                .call(testRequest);

        Response expectedResponse = new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:0",
                ""
        );
        assertEquals(expectedResponse, response);
    }

    @Test
    void itHandlesSimpleGetWithBody() {
        Request testRequest = new Request(
                "GET",
                "/simple_get_with_body",
                Map.of("Content-Length", "0")
        );

        Response response = new Router()
                .call(testRequest);

        Response expectedResponse = new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:11",
                "Hello world"
        );
        assertEquals(expectedResponse, response);
    }

    @Test
    void itHandlesNonexistentRoute() {
        Request testRequest = new Request(
                "GET",
                "/nonexistent_route",
                Map.of("Content-Length", "0")
        );

        Response response = new Router()
                .call(testRequest);

        Response expectedResponse = new Response(
                "HTTP/1.1",
                "404 Not Found",
                "Content-Length:0",
                ""
        );
        assertEquals(expectedResponse, response);
    }

    private class Router {
        private final String protocol = "HTTP/1.1";

        private Response call(Request request) {
            switch (request.path()) {
                case "/simple_get_with_body" -> {
                    return new Response(this.protocol, "200 OK", "Content-Length:11", "Hello world");
                }
                case "/simple_get", "/" -> {
                    return new Response(this.protocol, "200 OK", "Content-Length:0", "");
                }
                default -> {
                    return new Response(this.protocol, "404 Not Found", "Content-Length:0", "");
                }
            }
        }
    }
}
