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
        String testRequestString = "GET / HTTP/1.1\r\nContent-Length:0\r\n";
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send(testRequestString);

        Request testRequest = new Request(
                "GET / HTTP/1.1",
                Map.of(
                        "Content-Length",
                        "0"),
                ""
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        new HTTPServer(testRequestParser).call(testReaderWriter);

        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    @Test
    void itRespondsToSimpleGet() throws IOException {
        String testRequestString = "GET /simple_get HTTP/1.1\r\nContent-Length:0\r\n";
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send(testRequestString);

        Request testRequest = new Request(
                "GET /simple_get HTTP/1.1",
                Map.of(
                        "Content-Length",
                        "0"),
                ""
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        new HTTPServer(testRequestParser).call(testReaderWriter);

        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    @Test
    void itRespondsToSimpleGetWithBody() throws IOException {
        String testRequestString = "GET /simple_get_with_body HTTP/1.1\r\nContent-Length:0\r\n";
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send(testRequestString);

        Request testRequest = new Request(
                "GET /simple_get_with_body HTTP/1.1",
                Map.of(
                        "Content-Length",
                        "0"),
                ""
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        new HTTPServer(testRequestParser).call(testReaderWriter);

        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:11\r\n\nHello world"
        ), testReaderWriter.received());
    }

    @Test
    void itReturns404NotFoundForNonexistentResource() throws IOException {
        String testRequestString = "GET /nonexistent_resource HTTP/1.1\r\nContent-Length:0\r\n";
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send(testRequestString);

        Request testRequest = new Request(
                "GET /nonexistent_resource HTTP/1.1",
                Map.of(
                        "Content-Length",
                        "0"),
                ""
        );
        RequestParsable testRequestParser = new TestRequestParser(testRequest);

        new HTTPServer(testRequestParser).call(testReaderWriter);

        assertEquals(List.of(
                "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    @Test
    void itHandlesRepeatedRequests() throws IOException {
        String test200RequestString = "GET / HTTP/1.1\r\nContent-Length:0\r\n";
        String test404RequestString = "GET /nonexistent_resource HTTP/1.1\r\nContent-Length:0\r\n";
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send(test200RequestString)
                .send(test404RequestString);

        Request test200Request = new Request(
                "GET / HTTP/1.1",
                Map.of(
                        "Content-Length",
                        "0"),
                ""
        );
        Request test404Request = new Request(
                "GET /nonexistent_resource HTTP/1.1",
                Map.of(
                        "Content-Length",
                        "0"),
                ""
        );

        ArrayList<Request> testRequests = new ArrayList<>();
        testRequests.add(test200Request);
        testRequests.add(test404Request);

        RequestParsable testRequestParser = new ParsesRepeatedRequests(testRequests);

        HTTPServer httpServer = new HTTPServer(testRequestParser);
        httpServer.call(testReaderWriter);
        httpServer.call(testReaderWriter);

        assertEquals(List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:0\r\n\n",
                "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\n"
        ), testReaderWriter.received());
    }

    class TestRequestParser implements RequestParsable {
        private final Request request;

        public TestRequestParser(Request request) {
            this.request = request;
        }

        @Override
        public Request call(String rawRequest) {
            return request;
        }
    }

    private class ParsesRepeatedRequests implements RequestParsable {
        private final List<Request> requests;

        public ParsesRepeatedRequests(ArrayList<Request> requests) {
            this.requests = requests;
        }

        @Override
        public Request call(String rawRequest) {
            return requests.remove(0);
        }
    }
}