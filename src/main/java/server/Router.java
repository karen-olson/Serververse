package server;

import java.util.Map;

public class Router implements Handler {

    private final Map<String, Response> routes;
    private final Response notFoundResponse;

    public Router(Map<String, Response> routes, Response notFoundResponse) {

        this.routes = routes;
        this.notFoundResponse = notFoundResponse;
    }

    public Response call(Request request) {
        Response response = routes.get(request.method() + " " + request.path());

        return (response != null) ? response : notFoundResponse;
    }
}
