package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    Map<String, Handler> routes = Map.of(
            "GET /", new RootPathHandler(),
            "GET /simple_get", new SimpleGetHandler(),
            "HEAD /simple_get", new SimpleGetHandler(),
            "GET /simple_get_with_body", new SimpleGetWithBodyHandler(),
            "HEAD /head_request", new HeadRequestHandler(),
            "GET /redirect", new RedirectHandler()
    );

    @Test
    void itHandlesAnExistingResource() throws IOException {
        UUID apiKey = UUID.randomUUID();

        TestReaderWriter readerWriter = new TestReaderWriter()
                .send("GET /simple_get_with_body HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n");

        RequestParser requestParser = new RequestParser(new RequestLineParser(), new HeadersParser());
        Handler router = new Router(routes, new NotFoundHandler());
        ResponseWriteable responseWriter = new ResponseWriter();

        new HTTPServer(requestParser, router, responseWriter)
                .call(readerWriter);

        List<String> expectedResponse = List.of(
                "HTTP/1.1 200 OK\r\nContent-Length:11\r\n\r\nHello world"
        );

        assertEquals(expectedResponse, readerWriter.received());
    }

    @Test
    void itHandlesANonexistentResource() throws IOException {
        UUID apiKey = UUID.randomUUID();

        TestReaderWriter readerWriter = new TestReaderWriter()
                .send("GET /nonexistent_resource HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n");

        RequestParser requestParser = new RequestParser(new RequestLineParser(), new HeadersParser());
        Handler router = new Router(routes, new NotFoundHandler());
        ResponseWriteable responseWriter = new ResponseWriter();

        new HTTPServer(requestParser, router, responseWriter)
                .call(readerWriter);

        List<String> expectedResponse = List.of(
                "HTTP/1.1 404 Not Found\r\nContent-Length:0\r\n\r\n"
        );

        assertEquals(expectedResponse, readerWriter.received());
    }

    @Test
    void itHandlesRedirects() throws IOException {
        UUID apiKey = UUID.randomUUID();

        TestReaderWriter readerWriter = new TestReaderWriter()
                .send("GET /redirect HTTP/1.1\r\n")
                .send("Content-Length: 0\r\n")
                .send("Content-Type: text\r\n")
                .send(String.format("API-Key: %s\r\n", apiKey))
                .send("\r\n");

        RequestParser requestParser = new RequestParser(new RequestLineParser(), new HeadersParser());
        Handler router = new Router(routes, new NotFoundHandler());
        ResponseWriteable responseWriter = new ResponseWriter();

        new HTTPServer(requestParser, router, responseWriter)
                .call(readerWriter);

        List<String> expectedResponse = List.of(
                "HTTP/1.1 301 Moved Permanently\r\nLocation: http://127.0.0.1:5000/simple_get\r\n\r\n"
        );

        assertEquals(expectedResponse, readerWriter.received());
    }
}
