package server;

import java.io.IOException;

public class HTTPServer implements Application {

    private final String protocol;
    private final RequestParsable requestParser;
    private final ResponseWriteable responseWriter;

    public HTTPServer(RequestParsable requestParser, ResponseWriteable responseWriter) {
        this.responseWriter = responseWriter;
        this.protocol = "HTTP/1.1";
        this.requestParser = requestParser;
    }

    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        Request request = requestParser.call(readerWriter);
        Response response = route(request);
        responseWriter.call(readerWriter, response);
    }

    private Response route(Request request) {
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