package server;

import org.junit.jupiter.api.Test;

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
        ContentLengthParser contentLengthParser = new ContentLengthParser();
        BodyParser bodyParser = new BodyParser();

        Request request = new RequestParser(
                requestLineParser,
                headersParser,
                contentLengthParser,
                bodyParser
        )
                .call(readableWriteable);

        Request expectedRequest = new Request(
                "GET",
                "/path/to/resource",
                Map.of("content-length", "0",
                        "content-type", "text",
                        "api-key", apiKey.toString().toLowerCase()
                ),
                ""
        );

        assertEquals(expectedRequest, request);
    }

    @Test
    void itParsesARequestWithABody() throws IOException {
        UUID apiKey = UUID.randomUUID();
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("GET /path/to/resource HTTP/1.1\r\n")
                .send("Content-Length: 37\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n")
                .send("Some body content. \n")
                .send("More body content.");
        RequestLineParser requestLineParser = new RequestLineParser();
        HeadersParser headersParser = new HeadersParser();
        ContentLengthParser contentLengthParser = new ContentLengthParser();
        BodyParser bodyParser = new BodyParser();

        Request request = new RequestParser(
                requestLineParser,
                headersParser,
                contentLengthParser,
                bodyParser
        )
                .call(readableWriteable);

        Request expectedRequest = new Request(
                "GET",
                "/path/to/resource",
                Map.of("content-length", "37",
                        "content-type", "text",
                        "api-key", apiKey.toString()
                ),
                "Some body content. \nMore body content."
        );

        assertEquals(expectedRequest, request);
    }
}
