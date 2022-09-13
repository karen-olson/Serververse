package server;

import java.util.Map;

public record NewRequest(String method, String path, Map<String, String> headers) {
}
