package echoServer;

import java.net.ServerSocket;

/**
 * echoServer.Main runs the server.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            PortListenable portListener = new PortListener(serverSocket);
            Loopable infiniteLooper = block -> {
                while (true) {
                    block.call();
                }
            };

            new EchoServer(portListener, infiniteLooper).serve();
        }
    }
}