package server;

public record Response(String protocol, String statusCode, String headers, String body) {
}