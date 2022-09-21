package server;

import java.io.IOException;
import java.util.Map;

public class RequestParser implements RequestParsable {

    private final RequestLineParser requestLineParser;
    private final HeadersParser headersParser;

    public RequestParser(RequestLineParser requestLineParser, HeadersParser headersParser) {
        this.requestLineParser = requestLineParser;
        this.headersParser = headersParser;
    }

    public Request call(ReadableWriteable readableWriteable) throws IOException {
        RequestLineParser.RequestLine requestLine = requestLineParser.parse(readableWriteable);
        System.out.println("request line in RequestParser: " + requestLine);
        Map<String, String> headers = headersParser.parse(readableWriteable);

        return new Request(requestLine.method(), requestLine.path(), headers);
    }
}
