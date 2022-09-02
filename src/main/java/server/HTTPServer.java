package server;

import java.io.IOException;
import java.util.ArrayList;

public class HTTPServer implements Application {

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
        Response response;

        switch (path) {
            case "/simple_get_with_body" -> {
                response = new Response("200 OK", "Content-Length:11", "Hello world");
            }
            case "/simple_get", "/" -> {
                response = new Response("200 OK", "Content-Length:0", "");
            }
            default -> {
                response = new Response("404 Not Found", "Content-Length:0", "");
            }
        }

        readerWriter.writeLine(response.toString());
    }
}