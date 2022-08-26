package server;

import java.io.IOException;

public class HTTPServer implements Application {
    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        readerWriter.writeLine("HTTP/1.1 200 OK\n\nHello, world");
    }
}