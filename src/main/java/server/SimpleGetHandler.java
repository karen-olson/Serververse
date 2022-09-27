package server;

import java.util.Map;

public class SimpleGetHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "200 OK",
                Map.of("Content-Length", "0"),
                ""
        );
    }
}
