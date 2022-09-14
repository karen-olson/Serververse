package server;

import java.util.HashMap;
import java.util.Map;

public class Router implements Routable {
    private final String protocol = "HTTP/1.1";

    Map<String, Response> table = new HashMap<>();

    public Router() {
        table.put("/", new Response(this.protocol, "200 OK", "Content-Length:0", ""));
        table.put("/simple_get", new Response(this.protocol, "200 OK", "Content-Length:0", ""));
        table.put("/simple_get_with_body", new Response(this.protocol, "200 OK", "Content-Length:11", "Hello world"));
    }

    public Response call(Request request) {
        Response response = table.get(request.path());
        Response notFoundResponse = new Response(this.protocol, "404 Not Found", "Content-Length:0", "");

        return (response != null) ? response : notFoundResponse;
    }
}
