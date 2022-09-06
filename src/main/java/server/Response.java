package server;

public class Response {
    private final String protocol;
    private final String statusCode;
    private final String headers;
    private final String body;

    public Response(String protocol, String statusCode, String headers, String body) {
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}