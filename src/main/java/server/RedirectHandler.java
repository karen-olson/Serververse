package server;

public class RedirectHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "301 Moved Permanently",
                "Location: http://127.0.0.1:5000/simple_get",
                ""
        );
    }
}
