package server.httpAddOns.router;

import org.junit.jupiter.api.Test;
import server.httpAddOns.request.Request;
import server.httpAddOns.response.Response;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouterTest {

    final Map<String, Handler> testRoutes = Map.of(
            "GET /get", request -> new Response(
                    "protocol",
                    "statusCode",
                    "Route: GET /get",
                    ""
            ),
            "HEAD /get", request -> new Response(
                    "protocol",
                    "statusCode",
                    "Route: HEAD /get",
                    ""
            ),
            "HEAD /head", request -> new Response(
                    "protocol",
                    "statusCode",
                    "Route: HEAD /head",
                    ""
            )
    );

    Handler testNotFoundHandler = request -> new Response(
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

        Response response = new Router(testRoutes, testNotFoundHandler)
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

        Response response = new Router(testRoutes, testNotFoundHandler)
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