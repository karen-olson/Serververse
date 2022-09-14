package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HTTPServerTest {

    @Test
    void itReturns200OKForExistingResource() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("GET / HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n");

        Request testRequest = new Request(
                "GET",
                "/",
                Map.of(
                        "Content-Length",
                        "0")
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        String testResponse = "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n";
        ResponseWriteable testResponseWriter = new TestResponseWriter(testResponse);


        new HTTPServer(testRequestParser, testResponseWriter)
                .call(testReaderWriter);


        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    @Test
    void itRespondsToSimpleGet() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("GET /simple_get HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n");

        Request testRequest = new Request(
                "GET",
                "/simple_get",
                Map.of(
                        "Content-Length",
                        "0")
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        String testResponse = "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n";
        ResponseWriteable testResponseWriter = new TestResponseWriter(testResponse);


        new HTTPServer(testRequestParser, testResponseWriter)
                .call(testReaderWriter);


        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    @Test
    void itRespondsToSimpleGetWithBody() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("GET /simple_get_with_body HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n");

        Request testRequest = new Request(
                "GET",
                "/simple_get_with_body",
                Map.of(
                        "Content-Length",
                        "0")
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        String testResponse = "HTTP/1.1 200 OK\r\nContent-Length:11\r\n\nHello world";
        ResponseWriteable testResponseWriter = new TestResponseWriter(testResponse);


        new HTTPServer(testRequestParser, testResponseWriter)
                .call(testReaderWriter);


        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:11\r\n\nHello world"
        ), testReaderWriter.received());
    }

    @Test
    void itReturns404NotFoundForNonexistentResource() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("GET /nonexistent_resource HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n");

        Request testRequest = new Request(
                "GET",
                "/nonexistent_resource",
                Map.of(
                        "Content-Length",
                        "0")
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        String testResponse = "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\n";
        ResponseWriteable testResponseWriter = new TestResponseWriter(testResponse);


        new HTTPServer(testRequestParser, testResponseWriter)
                .call(testReaderWriter);


        assertEquals(List.of(
                "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    @Test
    void itHandlesHeadRequestToSimpleGet() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("HEAD /simple_get HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n");

        Request testRequest = new Request(
                "HEAD",
                "/simple_get",
                Map.of(
                        "Content-Length",
                        "0")
        );

        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        String testResponse = "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\r\n";
        ResponseWriteable testResponseWriter = new TestResponseWriter(testResponse);

        new HTTPServer(testRequestParser, testResponseWriter)
                .call(testReaderWriter);


        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\r\n"
        ), testReaderWriter.received());
    }

    @Test
    void itHandlesRepeatedRequests() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("GET / HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n")
                .send("GET /nonexistent_resource HTTP/1.1\r\n")
                .send("Content-Length:0")
                .send("\r\n");

        Request test200Request = new Request(
                "GET",
                "/",
                Map.of(
                        "Content-Length",
                        "0")
        );
        Request test404Request = new Request(
                "GET",
                "/nonexistent_resource",
                Map.of(
                        "Content-Length", "0"
                )
        );

        ArrayList<Request> testRequests = new ArrayList<>();
        testRequests.add(test200Request);
        testRequests.add(test404Request);
        RequestParsable testRequestParser = new ParsesRepeatedRequests(testRequests);

        String test200Response = "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n";
        String test404Response = "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\n";

        ArrayList<String> testResponses = new ArrayList<>();
        testResponses.add(test200Response);
        testResponses.add(test404Response);
        ResponseWriteable writesRepeatedResponses = new WritesRepeatedResponses(testResponses);

        HTTPServer httpServer = new HTTPServer(testRequestParser, writesRepeatedResponses);


        httpServer.call(testReaderWriter);
        httpServer.call(testReaderWriter);


        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n",
                "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    static class TestRequestParser implements RequestParsable {
        private final Request request;

        public TestRequestParser(Request request) {
            this.request = request;
        }

        @Override
        public Request call(ReadableWriteable readableWriteable) {
            return request;
        }
    }

    private static class ParsesRepeatedRequests implements RequestParsable {
        private final List<Request> requests;

        public ParsesRepeatedRequests(ArrayList<Request> requests) {
            this.requests = requests;
        }

        @Override
        public Request call(ReadableWriteable readableWriteable) {
            return requests.remove(0);
        }
    }

    private record WritesRepeatedResponses(ArrayList<String> responses) implements ResponseWriteable {

        @Override
        public void call(ReadableWriteable readerWriter, Response response) throws IOException {
            readerWriter.writeLine(responses.remove(0));
        }
    }

    private record TestResponseWriter(String testResponse) implements ResponseWriteable {

        @Override
        public void call(ReadableWriteable readerWriter, Response response) throws IOException {
            readerWriter.writeLine(testResponse);
        }
    }
}