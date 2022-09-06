package server;

import java.io.IOException;
import java.util.ArrayList;

public class HTTPServer implements Application {

    private final String protocol;

    public HTTPServer() {
        this.protocol = "HTTP/1.1";
    }

    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        ArrayList<String> request = readRequest(readerWriter);
        String path = getPath(request);
        writeResponse(path, readerWriter);
    }

    private ArrayList<String> readRequest(ReadableWriteable readerWriter) throws IOException {
        String line = readerWriter.readLine();
        ArrayList<String> request = new ArrayList<>();

        request.add(line);

        return request;
    }

    private String getPath(ArrayList<String> request) {
        return request.get(0).split("\\s+")[1];
    }

    private void writeResponse(String path, ReadableWriteable readerWriter) throws IOException {
        Response response = route(path);
        readerWriter.writeLine(format(response));
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

    private String format(Response response) {
        return response.getProtocol() + " " + response.getStatusCode() + "\r\n" + response.getHeaders() + "\r\n" + "\n" + response.getBody();
    }
}