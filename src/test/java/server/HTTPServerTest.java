package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTest {

    public static final String DATA_TO_THREAD_THROUGH_PIPELINE = "Data-to-thread-through-pipeline";

    @Test
    void itHandlesAnHTTPRequest() throws IOException {
        TestReaderWriter readerWriter = new TestReaderWriter();

        RequestParsable requestParser = readableWriteable ->
                new Request(
                        "GET",
                        "/",
                        Map.of(DATA_TO_THREAD_THROUGH_PIPELINE, "42"),
                        ""
                );

        Handler handler = request -> {
            String cookie = request.headers().get(DATA_TO_THREAD_THROUGH_PIPELINE);

            return new Response(
                    "HTTP/1.1",
                    "200 OK",
                    "%s: %s".formatted(DATA_TO_THREAD_THROUGH_PIPELINE, cookie),
                    ""
            );
        };

        ResponseWriteable responseWriter = (socketReaderWriter, response) -> socketReaderWriter.writeLine(
                "Valid response. %s: %s".formatted(DATA_TO_THREAD_THROUGH_PIPELINE,
                        response.headers().split(":")[1].trim()));


        new HTTPServer(requestParser, handler, responseWriter)
                .call(readerWriter);


        List<String> expectedResponse = List.of("Valid response. %s: 42".formatted(DATA_TO_THREAD_THROUGH_PIPELINE));

        assertEquals(expectedResponse, readerWriter.received());
    }
}