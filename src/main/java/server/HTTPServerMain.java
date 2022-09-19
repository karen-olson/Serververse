package server;

import java.net.ServerSocket;
import java.util.Map;

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

            Map<String, Handler> routes = Map.of(
                    "GET /", new RootPathHandler(),
                    "GET /simple_get", new SimpleGetHandler(),
                    "HEAD /simple_get", new SimpleGetHandler(),
                    "GET /simple_get_with_body", new SimpleGetWithBodyHandler(),
                    "HEAD /head_request", new HeadRequestHandler(),
                    "GET /redirect", new RedirectHandler()
            );

            RequestParser requestParser = new RequestParser(new RequestLineParser(), new HeadersParser());
            Handler router = new Router(routes, new NotFoundHandler());
            ResponseWriteable responseWriter = new ResponseWriter();
            Application httpServer = new HTTPServer(requestParser, router, responseWriter);

            new Server(infiniteLooper, portListener).serve(httpServer);
        }
    }
}