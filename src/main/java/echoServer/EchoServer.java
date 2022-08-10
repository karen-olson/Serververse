package echoServer;

import java.io.IOException;

public class EchoServer {

    private final PortListenable portListener;

    public EchoServer(PortListenable portListener) {
        this.portListener = portListener;
    }

    public void serve() throws IOException {
        ReadableWriteable socketReaderWriter = portListener.listen();
        String message = socketReaderWriter.readLine();
        socketReaderWriter.writeLine(message);
    }
}