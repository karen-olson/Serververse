package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTPServer implements Application {
    private final List<String> paths = List.of("/");

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
        if (paths.contains(path)) {
            readerWriter.writeLine("HTTP/1.1 200 OK\r\nContent-Length:0\r\n");
        } else {
            readerWriter.writeLine("HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n");
        }
    }
}