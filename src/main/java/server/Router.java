package server;

import java.util.Map;

public class Router implements Handler {

    private final Map<String, Handler> routes;
    private final Response notFoundResponse;

    public Router(Map<String, Handler> routes, Response notFoundResponse) {

        this.routes = routes;
        this.notFoundResponse = notFoundResponse;
    }

    public Response call(Request request) {
        Handler handler = routes.get(request.method() + " " + request.path());

        if (handler != null) {
            return handler.call(request);
        } else {
            return notFoundResponse;
        }
    }
}
