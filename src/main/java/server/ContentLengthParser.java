package server;

import java.util.Map;

public class ContentLengthParser {
    public int parse(Map<String, String> headers) {
        if (headers.containsKey("content-length")) {
            return Integer.parseInt(headers.get("content-length"));
        }
        return -1;
    }
}
