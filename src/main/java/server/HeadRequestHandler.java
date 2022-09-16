package server;

public class HeadRequestHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:0",
                ""
        );
    }
}
