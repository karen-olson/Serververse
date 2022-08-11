package echoServer;

import java.io.IOException;

public class EchoServer {

    private final PortListenable portListener;

    public EchoServer(PortListenable portListener) {
        this.portListener = portListener;
    }

    public void serve() throws IOException {
        ReadableWriteable socketReaderWriter = portListener.listen();

        String message;
        while ((message = socketReaderWriter.readLine()) != null) {
            socketReaderWriter.writeLine(message);
        }
    }
}