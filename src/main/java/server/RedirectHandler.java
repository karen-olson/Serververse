package server;

import java.util.Map;

public class RedirectHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "301 Moved Permanently",
                Map.of("Location", "http://127.0.0.1:5000/simple_get"),
                ""
        );
    }
}
