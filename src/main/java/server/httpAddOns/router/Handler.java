package server.httpAddOns.router;

import server.httpAddOns.request.Request;
import server.httpAddOns.response.Response;

public interface Handler {
    Response call(Request request);
}
