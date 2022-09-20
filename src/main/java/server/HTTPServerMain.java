package server;

import java.net.ServerSocket;

/**
 * Main runs the server.
 */
public class HTTPServerMain {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            PortListenable portListener = new PortListener(serverSocket);
            Loopable infiniteLooper = block -> {
                //noinspection InfiniteLoopStatement
                while (true) {
                    block.call();
                }
            };

            RequestParser requestParser = new RequestParser(new RequestLineParser(), new HeadersParser());
            Handler router = HTTPServerSpecRouterFactory.create();
            ResponseWriteable responseWriter = new ResponseWriter();

            Application httpServer = new HTTPServer(requestParser, router, responseWriter);

            new Server(infiniteLooper, portListener).serve(httpServer);
        }
    }
}