package server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NewRequestParser {
    public NewRequest call(ReadableWriteable readableWriteable) throws IOException {
        RequestLine requestLine = parseRequestLine(readableWriteable);

        return new NewRequest(requestLine.method, requestLine.path);
    }

    private RequestLine parseRequestLine(ReadableWriteable readableWriteable) throws IOException {
        String line = readableWriteable.readLine();

        List<String> rawRequestLine = Arrays.asList(line.split("\\s"));

        String method = rawRequestLine.get(0);
        String path = rawRequestLine.get(1);

        return new RequestLine(method, path);
    }

    public record NewRequest(String method, String path) {
    }

    record RequestLine(String method, String path) {
    }
}
