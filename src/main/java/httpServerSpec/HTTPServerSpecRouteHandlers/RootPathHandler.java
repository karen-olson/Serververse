package httpServerSpec.HTTPServerSpecRouteHandlers;

import server.httpAddOns.request.Request;
import server.httpAddOns.response.Response;
import server.httpAddOns.router.Handler;

public class RootPathHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "200 OK",
                "Content-Length:0",
                ""
        );
    }
}
