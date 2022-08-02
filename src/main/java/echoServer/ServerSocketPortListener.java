package echoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

interface SocketCreator {

}

public class ServerSocketPortListener implements PortListener {
    private final ServerSocket serverSocket;

    public ServerSocketPortListener() throws IOException {
        int port = 3000;
        this.serverSocket = new ServerSocket(port);
    }

    public Socket accept() throws IOException {
        Socket clientSocket = this.serverSocket.accept();
        return clientSocket;
    }

    @Override
    public void stopListening() throws IOException {
        this.serverSocket.close();
    }
}