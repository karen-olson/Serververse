package server;

import java.util.Map;

public record Request(
        String requestLine,
        Map<String, String> headers,
        String body
) {
}