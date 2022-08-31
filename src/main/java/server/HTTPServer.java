package server;

import java.io.IOException;
import java.util.ArrayList;

public class HTTPServer implements Application {
    ArrayList<String> request = new ArrayList<>();
    ArrayList<String> resources = new ArrayList<>();
    String requestedResource;

    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        readRequest(readerWriter);
        parseRequest();
        writeResponse(readerWriter);
    }

    private void readRequest(ReadableWriteable readerWriter) throws IOException {
        String line;
        line = readerWriter.readLine();
        request.add(line);
    }

    private void parseRequest() {
        requestedResource = getRequestedResource();
    }

    private String getRequestedResource() {
        return request.get(0).split("\\s+")[1];
    }

    private void writeResponse(ReadableWriteable readerWriter) throws IOException {
        if (resources.contains(requestedResource)) {
            readerWriter.writeLine("HTTP/1.1 200 OK\r\nContent-Length:0\r\n");
        } else {
            readerWriter.writeLine("HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n");
        }
    }
}