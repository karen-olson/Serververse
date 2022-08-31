package server;

import java.io.IOException;
import java.util.ArrayList;

public class HTTPServer implements Application {
    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        String line;
        ArrayList<String> request = new ArrayList<>();
        ArrayList<String> resources = new ArrayList<>();

        while ((line = readerWriter.readLine()) != null) {
            request.add(line);
        }

        String requested_resource = request.get(0).split("\\s+")[1];

        if (resources.contains(requested_resource)) {
            readerWriter.writeLine("HTTP/1.1 200 OK\r\nContent-Length:0\r\n");
        } else {
            readerWriter.writeLine("HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n");
        }
    }
}