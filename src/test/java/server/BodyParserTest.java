package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodyParserTest {
    @Test
    void itParsesARequestBodyWithWhitespace() throws IOException {
        ReadableWriteable readerWriter = new TestReaderWriter()
                .send("Line 1: hi \r")
                .send("Line 2. \n")
                .send("Line 3. \r\n")
                .send("Line 4.");

        int contentLength = 38;

        String body = new BodyParser().parse(readerWriter, contentLength);

        assertEquals("Line 1: hi \rLine 2. \nLine 3. \r\nLine 4.", body);
    }

    @Test
    void itParsesARequestBodyWithoutWhitespace() throws IOException {
        ReadableWriteable readerWriter = new TestReaderWriter()
                .send("Hello world");

        int contentLength = 11;

        String body = new BodyParser().parse(readerWriter, contentLength);

        assertEquals("Hello world", body);
    }
}
