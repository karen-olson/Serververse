package server.httpAddOns.request.RequestParser;

import server.basicServer.socketConnection.ReadableWriteable;
import server.httpAddOns.request.Request;

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
        Map<String, String> headers = headersParser.parse(readableWriteable);

        return new Request(requestLine.method(), requestLine.path(), headers);
    }
}
