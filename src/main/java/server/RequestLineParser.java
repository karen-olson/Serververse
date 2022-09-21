package server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RequestLineParser {

    public RequestLine parse(ReadableWriteable readableWriteable) throws IOException {
        String line = readableWriteable.readLine();
        List<String> rawRequestLine = Arrays.asList(line.split("\\s"));

        String method = rawRequestLine.get(0);
        String path = rawRequestLine.get(1);

        return new RequestLine(method, path);
    }

    public record RequestLine(String method, String path) {
    }
}
