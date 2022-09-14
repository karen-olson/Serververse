package server;

public class Router implements Routable {
    private final String protocol = "HTTP/1.1";

    public Response call(Request request) {
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
