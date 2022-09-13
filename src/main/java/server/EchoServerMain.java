package server;

import java.net.ServerSocket;

/**
 * echoServer.Main runs the server.
 */
public class EchoServerMain {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            PortListenable portListener = new PortListener(serverSocket);
            Loopable infiniteLooper = block -> {
                while (true) {
                    block.call();
                }
            };
            Application echoServer = new EchoServer();

            new Server(infiniteLooper, portListener).serve(echoServer);
        }
    }
}