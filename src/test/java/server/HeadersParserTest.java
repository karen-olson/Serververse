package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadersParserTest {

    @Test
    void itParsesASingleHeader() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        Map<String, String> header = new HeadersParser()
                .parse(readableWriteable);

        assertEquals(Map.of("content-length", "0"), header);
    }

    @Test
    void headersAreCaseInsensitive() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("CONTENT-length: 0\r\n")
                .send("\r\n");

        Map<String, String> header = new HeadersParser()
                .parse(readableWriteable);

        assertEquals(Map.of("content-length", "0"), header);
    }

    @Test
    void itParsesMultipleHeaders() throws IOException {
        UUID apiKey = UUID.randomUUID();
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("Content-Length: 0\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n");

        Map<String, String> headers = new HeadersParser()
                .parse(readableWriteable);

        assertEquals(Map.of(
                "content-length", "0",
                "content-type", "text",
                "api-key", apiKey.toString()
        ), headers);
    }

    @Test
    void itParsesHeaderSectionThatEndsWithCRLF() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("Content-Length: 0\r\n")
                .send("\r\n");

        Map<String, String> header = new HeadersParser()
                .parse(readableWriteable);

        assertEquals(Map.of("content-length", "0"), header);
    }

    @Test
    void itParsesHeaderSectionThatEndsWithEmptyString() throws IOException {
        ReadableWriteable readableWriteable = new TestReaderWriter()
                .send("Content-Length: 0\r\n")
                .send("");

        Map<String, String> header = new HeadersParser()
                .parse(readableWriteable);

        assertEquals(Map.of("content-length", "0"), header);
    }
}
