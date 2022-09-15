package server;

import java.util.Map;

public class Router implements Routable {
    private final String protocol = "HTTP/1.1";

    Map<String, Response> routes = Map.of(
            "GET /", new Response(
                    protocol,
                    "200 OK",
                    "Content-Length:0",
                    ""
            ),
            "GET /simple_get", new Response(
                    protocol,
                    "200 OK",
                    "Content-Length:0",
                    ""
            ),
            "HEAD /simple_get", new Response(
                    protocol,
                    "200 OK",
                    "Content-Length:0",
                    ""
            ),
            "GET /simple_get_with_body", new Response(
                    protocol,
                    "200 OK",
                    "Content-Length:11",
                    "Hello world"
            ),
            "HEAD /head_request", new Response(
                    protocol,
                    "200 OK",
                    "Content-Length:0",
                    ""
            )
    );

    public Response call(Request request) {
        Response response = routes.get(request.method() + " " + request.path());
        Response notFoundResponse = new Response(this.protocol, "404 Not Found", "Content-Length:0", "");

        return (response != null) ? response : notFoundResponse;
    }
}
