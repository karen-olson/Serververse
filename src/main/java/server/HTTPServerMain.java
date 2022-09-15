package server;

import java.net.ServerSocket;
import java.util.Map;

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

            Map<String, Handler> routes = Map.of(
                    "GET /", new rootPathHandler(),
                    "GET /simple_get", new simpleGetHandler(),
                    "HEAD /simple_get", new simpleGetHandler(),
                    "GET /simple_get_with_body", new simpleGetWithBodyHandler(),
                    "HEAD /head_request", new headRequestHandler()
            );
            Response notFoundResponse = new Response("HTTP/1.1", "404 Not Found", "Content-Length:0", "");

            RequestParser requestParser = new RequestParser(new RequestLineParser(), new HeadersParser());
            Handler router = new Router(routes, notFoundResponse);
            ResponseWriteable responseWriter = new ResponseWriter();
            Application httpServer = new HTTPServer(requestParser, router, responseWriter);

            new Server(infiniteLooper, portListener).serve(httpServer);
        }
    }
}