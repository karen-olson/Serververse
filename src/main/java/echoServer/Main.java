package echoServer;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * echoServer.Main runs the server.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        PortListenable portListener = new PortListener(serverSocket);
        new EchoServer(portListener).serve();
    }
}