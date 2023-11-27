package server.httpAddOns.request;

import org.junit.jupiter.api.Test;
import server.basicServer.socketConnection.ReadableWriteable;
import server.basicServer.socketConnection.TestReaderWriter;
import server.httpAddOns.request.RequestParser.HeadersParser;
import server.httpAddOns.request.RequestParser.RequestLineParser;
import server.httpAddOns.request.RequestParser.RequestParser;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {

    @Test
    void itParsesARequestWithNoBody() throws IOException {
        UUID apiKey = UUID.randomUUID();
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET /path/to/resource HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n");
        RequestLineParser requestLineParser = new RequestLineParser();
        HeadersParser headersParser = new HeadersParser();

        Request request = new RequestParser(requestLineParser, headersParser)
                .call(readableWriteable);

        Request expectedRequest = new Request(
                "GET",
                "/path/to/resource",
                Map.of("Content-Length", "0",
                        "Content-Type", "text",
                        "API-Key", apiKey.toString()
                )
        );

        assertEquals(expectedRequest, request);
    }
}
