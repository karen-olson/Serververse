package server;

public class ResponseWriter implements ResponseWriteable {

    public ResponseWriter() {
    }

    public void call(ReadableWriteable readerWriter, Response response) {
        readerWriter.writeLine(format(response));
    }

    private String format(Response response) {
        String space = " ";
        String CRLF = "\r\n";

        return response.protocol() + space + response.statusCode() + CRLF +
                response.headers() + CRLF +
                CRLF +
                response.body();
    }
}
