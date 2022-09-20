package server;

import java.io.IOException;
import java.util.Map;

public class RequestParser implements RequestParsable {

    private final RequestLineParser requestLineParser;
    private final HeadersParser headersParser;
    private final BodyParser bodyParser;
    private final ContentLengthParser contentLengthParser;

    public RequestParser(RequestLineParser requestLineParser, HeadersParser headersParser, ContentLengthParser contentLengthParser, BodyParser bodyParser) {
        this.requestLineParser = requestLineParser;
        this.headersParser = headersParser;
        this.contentLengthParser = contentLengthParser;
        this.bodyParser = bodyParser;
    }

    public Request call(ReadableWriteable readableWriteable) throws IOException {
        RequestLineParser.RequestLine requestLine = requestLineParser.parse(readableWriteable);
        Map<String, String> headers = headersParser.parse(readableWriteable);
        Integer contentLength = contentLengthParser.parse(headers);
        String body = bodyParser.parse(readableWriteable, contentLength);

        return new Request(requestLine.method(), requestLine.path(), headers, body);
    }
}
