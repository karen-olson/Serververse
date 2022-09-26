package httpServerSpec.HTTPServerSpecRouteHandlers;

import server.httpAddOns.request.Request;
import server.httpAddOns.response.Response;
import server.httpAddOns.router.Handler;

public class NotFoundHandler implements Handler {

    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "404 Not Found",
                "Content-Length:0",
                ""
        );
    }
}


