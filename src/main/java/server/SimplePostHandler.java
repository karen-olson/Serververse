package server;

import java.util.HashMap;
import java.util.Map;

public class SimplePostHandler implements Handler {

    public Response call(Request request) {
        return new Response(
                "HTTP/1.1",
                "200 OK",
                responseHeaders(request),
                request.body()
        );
    }

    private Map<String, String> responseHeaders(Request request) {
        Map<String, String> requestHeaders = request.headers();
        Map<String, String> responseHeaders = new HashMap<>();

        responseHeaders.put("Content-Length", requestHeaders.get("content-length"));
        responseHeaders.put("Content-Type", requestHeaders.get("content-type"));

        return responseHeaders;
    }
}
