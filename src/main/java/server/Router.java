package server;

import java.util.HashMap;
import java.util.Map;

public class Router implements Routable {
    private final String protocol = "HTTP/1.1";

    Map<String, Response> routes = new HashMap<>();

    public Router() {
        populateRoutes();
    }

    public Response call(Request request) {
        Response response = routes.get(request.path());
        Response notFoundResponse = new Response(this.protocol, "404 Not Found", "Content-Length:0", "");

        return (response != null) ? response : notFoundResponse;
    }

    private void populateRoutes() {
        routes.put("/", new Response(this.protocol, "200 OK", "Content-Length:0", ""));
        routes.put("/simple_get", new Response(this.protocol, "200 OK", "Content-Length:0", ""));
        routes.put("/simple_get_with_body", new Response(this.protocol, "200 OK", "Content-Length:11", "Hello world"));
        routes.put("/head_request", new Response(this.protocol, "200 OK", "Content-Length:0", ""));
    }
}
