package server;

import java.util.Map;

public class HTTPServerSpecRouterFactory {
    private static final Map<String, Handler> routes = Map.of(
            "GET /", new RootPathHandler(),
            "GET /simple_get", new SimpleGetHandler(),
            "HEAD /simple_get", new SimpleGetHandler(),
            "GET /simple_get_with_body", new SimpleGetWithBodyHandler(),
            "HEAD /head_request", new HeadRequestHandler(),
            "GET /redirect", new RedirectHandler()
    );

    public static Router create() {
        return new Router(routes, new NotFoundHandler());
    }
}
