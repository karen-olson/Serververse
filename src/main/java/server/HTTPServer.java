package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTPServer implements Application {
    private final List<String> resources = List.of("/");

    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        ArrayList<String> request = readRequest(readerWriter);
        String requestedResource = parseRequest(request);
        writeResponse(requestedResource, readerWriter);
    }

    private ArrayList<String> readRequest(ReadableWriteable readerWriter) throws IOException {
        String line = readerWriter.readLine();
        ArrayList<String> request = new ArrayList<>();

        request.add(line);

        return request;
    }

    private String parseRequest(ArrayList<String> request) {
        String requestedResource = request.get(0).split("\\s+")[1];
        return requestedResource;
    }

    private void writeResponse(String requestedResource, ReadableWriteable readerWriter) throws IOException {
        if (resources.contains(requestedResource)) {
            readerWriter.writeLine("HTTP/1.1 200 OK\r\nContent-Length:0\r\n");
        } else {
            readerWriter.writeLine("HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n");
        }
    }
}