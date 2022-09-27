package server;

public record Response(String protocol, String statusCode, java.util.Map<String, String> headers, String body) {
}