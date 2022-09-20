package server;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentLengthParserTest {
    @Test
    void itReturnsContentLength() {
        UUID apiKey = UUID.randomUUID();
        Map<String, String> headers = Map.of(
                "content-length", "42",
                "content-type", "text/xml; charset=utf-8",
                "api-key", apiKey.toString()
        );

        int contentLength = new ContentLengthParser()
                .parse(headers);

        assertEquals(42, contentLength);
    }

    @Test
    void itReturnsNegativeOneIfNoContentLength() {
        UUID apiKey = UUID.randomUUID();
        Map<String, String> headers = Map.of(
                "Content-Type", "text/xml; charset=utf-8",
                "API-Key", apiKey.toString()
        );

        int contentLength = new ContentLengthParser()
                .parse(headers);

        assertEquals(-1, contentLength);
    }
}
