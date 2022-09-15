package server;

import java.io.IOException;

public class HTTPServer implements Application {

    private final RequestParsable requestParser;
    private final ResponseWriteable responseWriter;
    private final Handler router;

    public HTTPServer(RequestParsable requestParser, Handler router, ResponseWriteable responseWriter) {
        this.router = router;
        this.responseWriter = responseWriter;
        this.requestParser = requestParser;
    }

    @Override
    public void call(ReadableWriteable readerWriter) throws IOException {
        Request request = requestParser.call(readerWriter);
        Response response = router.call(request);
        responseWriter.call(readerWriter, response);
    }
}