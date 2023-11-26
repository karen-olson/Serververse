package server;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    final Map<String, Handler> testRoutes = Map.of(
            "GET /get", request -> new Response(
                    "protocol",
                    "statusCode",
                    Map.of("Route", "GET /get"),
                    ""
            ),
            "HEAD /get", request -> new Response(
                    "protocol",
                    "statusCode",
                    Map.of("Route", "HEAD /get"),
                    ""
            ),
            "HEAD /head", request -> new Response(
                    "protocol",
                    "statusCode",
                    Map.of("Route", "HEAD /head"),
                    ""
            )
    );

    private final Handler testNotFoundHandler = request -> new Response(
            "protocol",
            "404 Not Found",
            Map.of("Route", "not found"),
            ""
    );

    @Test
    void itHandlesAnExistingResource() {
        Request testRequest = new Request(
                "HEAD",
                "/get",
                Map.of("Content-length", "0"),
                ""
        );

        Response response = new Router(testRoutes, testNotFoundHandler)
                .call(testRequest);

        Response expectedResponse = new Response(
                "protocol",
                "statusCode",
                Map.of("Route", "HEAD /get"),
                ""
        );

        assertEquals(expectedResponse, response);
    }

    @Test
    void itHandlesANonexistentResource() {
        Request testRequest = new Request(
                "GET",
                "/nonexistent_route",
                Map.of("Content-Length", "0"),
                ""
        );

        Response response = new Router(testRoutes, testNotFoundHandler)
                .call(testRequest);

        Response expectedResponse = new Response(
                "protocol",
                "404 Not Found",
                Map.of("Route", "not found"),
                ""
        );
        assertEquals(expectedResponse, response);
    }
}