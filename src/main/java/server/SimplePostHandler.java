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
        Map<String, String> responseHeaders = new HashMap<>();

        addContentLengthHeader(request, responseHeaders);
        addContentTypeHeader(request, responseHeaders);

        return responseHeaders;
    }

    private void addContentLengthHeader(Request request, Map<String, String> responseHeaders) {
        responseHeaders.put("Content-Length", String.valueOf(request.body().length()));
    }

    private void addContentTypeHeader(Request request, Map<String, String> responseHeaders) {
        String contentType;
        if ((contentType = request.headers().get("content-type")) != null) {
            responseHeaders.put("Content-Type", contentType);
        } else {
            responseHeaders.put("Content-Type", "text/plain");
        }
    }
}
