package httpServerSpec.HTTPServerSpecRouteHandlers;

import server.httpAddOns.request.Request;
import server.httpAddOns.response.Response;
import server.httpAddOns.router.Handler;

public class RedirectHandler implements Handler {
    @Override
    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "301 Moved Permanently",
                "Location: http://127.0.0.1:5000/simple_get",
                ""
        );
    }
}
