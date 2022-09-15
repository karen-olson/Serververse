package server;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    Map<String, Response> testRoutes = Map.of(
            "GET /get", new Response(
                    "protocol",
                    "statusCode",
                    "Route: GET /get",
                    ""
            ),
            "HEAD /get", new Response(
                    "protocol",
                    "statusCode",
                    "Route: HEAD /get",
                    ""
            ),
            "HEAD /head", new Response(
                    "protocol",
                    "statusCode",
                    "Route: HEAD /head",
                    ""
            )
    );

    Response testNotFoundResponse = new Response(
            "protocol",
            "404 Not Found",
            "Route: not found",
            ""
    );


    @Test
    void itHandlesAnExistingResource() {
        Request testRequest = new Request(
                "HEAD",
                "/get",
                Map.of("Content-length", "0")
        );

        Response response = new Router(testRoutes, testNotFoundResponse)
                .call(testRequest);

        Response expectedResponse = new Response(
                "protocol",
                "statusCode",
                "Route: HEAD /get",
                ""
        );

        assertEquals(expectedResponse, response);
    }

    @Test
    void itHandlesANonexistentResource() {
        Request testRequest = new Request(
                "GET",
                "/nonexistent_route",
                Map.of("Content-Length", "0")
        );

        Response response = new Router(testRoutes, testNotFoundResponse)
                .call(testRequest);

        Response expectedResponse = new Response(
                "protocol",
                "404 Not Found",
                "Route: not found",
                ""
        );
        assertEquals(expectedResponse, response);
    }
}