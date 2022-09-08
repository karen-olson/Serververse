package server;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {

    @Test
    void itParsesARequestWithNoBody() {
        String rawRequest = "GET /simple_get HTTP/1.1\r\nContent-Length:0\r\n\r\n";
        RequestParser parser = new RequestParser();

        Request parsedRequest = parser.call(rawRequest);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "0");

        Request expectedRequest = new Request(
                "GET /simple_get HTTP/1.1",
                expectedHeaders,
                ""
        );

        assertEquals(expectedRequest, parsedRequest);
    }

    @Test
    void itParsesARequestWithABody() {
        String rawRequestWithBody = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length:11\r\n\r\nHello world";

        RequestParser parser = new RequestParser();

        Request parsedRequest = parser.call(rawRequestWithBody);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "11");

        Request requestWithBody = new Request(
                "GET /simple_get_with_body HTTP/1.1",
                expectedHeaders,
                "Hello world"
        );

        assertEquals(requestWithBody, parsedRequest);
    }

    @Test
    void itParsesARequestWithAMultilineBody() {
        String rawRequestWithMultilineBody = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length:11\r\n\r\nHello world\nHow are you?";

        RequestParser parser = new RequestParser();

        Request parsedRequest = parser.call(rawRequestWithMultilineBody);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "11");

        Request requestWithBody = new Request(
                "GET /simple_get_with_body HTTP/1.1",
                expectedHeaders,
                "Hello world\nHow are you?"
        );

        assertEquals(requestWithBody, parsedRequest);
    }

    @Test
    void itParsesARequestWithMultipleHeaders() {
        String rawRequestWithMultipleHeaders = "GET / HTTP/1.1\r\nContent-Length:11\r\nContent-Type:text/plain; charset=UTF-8\r\n\r\nHello world";

        RequestParser parser = new RequestParser();

        Request parsedRequest = parser.call(rawRequestWithMultipleHeaders);

        HashMap<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Length", "11");
        expectedHeaders.put("Content-Type", "text/plain; charset=UTF-8");

        Request requestWithMultipleHeaders = new Request(
                "GET / HTTP/1.1",
                expectedHeaders,
                "Hello world"
        );

        assertEquals(requestWithMultipleHeaders, parsedRequest);
    }
}
