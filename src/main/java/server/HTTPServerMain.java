package server;

import java.net.ServerSocket;

/**
 * echoServer.Main runs the server.
 */
public class HTTPServerMain {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            PortListenable portListener = new PortListener(serverSocket);
            Loopable infiniteLooper = block -> {
                while (true) {
                    block.call();
                }
            };
            
            NewRequestParser requestParser = new NewRequestParser(new RequestLineParser(), new HeadersParser());
            Application httpServer = new HTTPServer(requestParser);

            new MyServer(infiniteLooper, portListener).serve(httpServer);
        }
    }
}