package server;

public class SimpleGetWithBodyHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:11",
                "Hello world"
        );
    }
}
