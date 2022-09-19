package server;

import java.util.Map;

public class Router implements Handler {

    private final Map<String, Handler> routes;
    private final Handler notFoundHandler;

    public Router(Map<String, Handler> routes, Handler notFoundHandler) {

        this.routes = routes;
        this.notFoundHandler = notFoundHandler;
    }

    public Response call(Request request) {
        Handler handler = routes.get(request.method() + " " + request.path());

        if (handler != null) {
            return handler.call(request);
        } else {
            return notFoundHandler.call(request);
        }
    }
}
