package server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NewRequestParser {

    public static final String CRLF = "\r\n";

    public NewRequest call(ReadableWriteable readableWriteable) throws IOException {
        RequestLine requestLine = parseRequestLine(readableWriteable);
        Map<String, String> headers = parseHeaders(readableWriteable);

        return new NewRequest(requestLine.method, requestLine.path, headers);
    }


    private Map<String, String> parseHeaders(ReadableWriteable readableWriteable) throws IOException {
        String rawHeader = readableWriteable.readLine();
        Map<String, String> headers = new java.util.HashMap<>(Map.of());

        while (additionalHeadersRemain(rawHeader)) {
            Header header = parseHeader(rawHeader);
            headers.put(header.key, header.value);
            rawHeader = readableWriteable.readLine();
        }

        return headers;
    }

    private boolean additionalHeadersRemain(String rawHeader) {
        return !rawHeader.equals(CRLF);
    }

    private Header parseHeader(String header) {
        List<String> parsedHeader = Arrays.asList(header.split(":"));
        String key = parsedHeader.get(0).trim();
        String value = parsedHeader.get(1).trim();

        return new Header(key, value);
    }

    private RequestLine parseRequestLine(ReadableWriteable readableWriteable) throws IOException {
        String line = readableWriteable.readLine();

        List<String> rawRequestLine = Arrays.asList(line.split("\\s"));

        String method = rawRequestLine.get(0);
        String path = rawRequestLine.get(1);

        return new RequestLine(method, path);
    }

    private record Header(String key, String value) {
    }

    public record NewRequest(String method, String path, Map<String, String> headers) {
    }

    record RequestLine(String method, String path) {
    }
}
