package server.basicServer.socketConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PortListener implements PortListenable {

    private final ServerSocket serverSocket;

    public PortListener(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public ReadableWriteable listen() throws IOException {
        Socket socket = serverSocket.accept();
        return new SocketReaderWriter(socket);
    }
}
