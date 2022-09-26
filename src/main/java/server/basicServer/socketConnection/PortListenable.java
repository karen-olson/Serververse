package server.basicServer.socketConnection;

import java.io.IOException;

public interface PortListenable {
    ReadableWriteable listen() throws IOException;
}
