package server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HeadersParser {
    public Map<String, String> parse(ReadableWriteable readableWriteable) throws IOException {
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
        boolean isCRLF = rawHeader.equals(RequestParser.CRLF);
        boolean isEmptyString = rawHeader.equals("");

        return !(isCRLF || isEmptyString);
    }

    private Header parseHeader(String header) {
        List<String> parsedHeader = Arrays.asList(header.split(":"));
        String key = parsedHeader.get(0).trim();
        String value = parsedHeader.get(1).trim();

        return new Header(key, value);
    }

    record Header(String key, String value) {
    }
}
