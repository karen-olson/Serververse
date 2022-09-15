package server;

public class rootPathHandler implements Handler {
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
