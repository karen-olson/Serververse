package server.httpAddOns.router;

import server.httpAddOns.request.Request;
import server.httpAddOns.response.Response;

import java.util.Map;

public class Router implements Handler {

    private final Map<String, Handler> routes;
    private final Handler notFoundHandler;

    public Router(Map<String, Handler> routes, Handler notFoundHandler) {

        this.routes = routes;
        this.notFoundHandler = notFoundHandler;
    }

    public Response call(Request request) {
        String route = request.method() + " " + request.path();
        Handler handler = routes.getOrDefault(route, notFoundHandler);

        return handler.call(request);
    }
}
