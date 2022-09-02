package server;

public class Response {
    private final String protocol;
    private final String statusCode;
    private final String headers;
    private final String body;
    private final String responseString;

    public Response(String statusCode, String headers, String body) {
        this.protocol = "HTTP/1.1";
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.responseString = createResponseString();
    }

    public String toString() {
        return this.responseString;
    }

    private String createResponseString() {
        return protocol + " " + statusCode + "\r\n" + headers + "\r\n" + "\n" + body;
    }
}