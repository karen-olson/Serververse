package server;

import java.io.IOException;
import java.util.Map;

public class NewRequestParser implements RequestParsable {

    public static final String CRLF = "\r\n";
    private final RequestLineParser requestLineParser;
    private final HeadersParser headersParser;

    public NewRequestParser(RequestLineParser requestLineParser, HeadersParser headersParser) {
        this.requestLineParser = requestLineParser;
        this.headersParser = headersParser;
    }

    public NewRequest call(ReadableWriteable readableWriteable) throws IOException {
        RequestLineParser.RequestLine requestLine = requestLineParser.parse(readableWriteable);
        Map<String, String> headers = headersParser.parse(readableWriteable);

        return new NewRequest(requestLine.method(), requestLine.path(), headers);
    }
}
