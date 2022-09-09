package server;

import java.io.IOException;

public class HTTPServer implements Application {

    private final String protocol;
    private final RequestParsable requestParser;

    public HTTPServer(RequestParsable requestParser) {
        this.protocol = "HTTP/1.1";
        this.requestParser = requestParser;
    }

    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        Request request = requestParser.call(readRequest(readerWriter));
        Response response = route(getPath(request));
        response.write(readerWriter);
    }

    private String getPath(Request request) {
        return request.requestLine().split("\\s")[1];
    }

    private String readRequest(ReadableWriteable readerWriter) throws IOException {
        String request = "";
        request += readerWriter.readLine() + "\r\n";
        request += readerWriter.readLine() + "\r\n\r\n";

        return request;
    }

    private Response route(String path) {
        switch (path) {
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