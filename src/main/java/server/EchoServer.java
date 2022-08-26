package server;

import java.io.IOException;

public class EchoServer implements Application {

    @Override
    public void call(ReadableWriteable socketReaderWriter) throws IOException {
        String message;
        while ((message = socketReaderWriter.readLine()) != null) {
            socketReaderWriter.writeLine(message);
        }
    }
}