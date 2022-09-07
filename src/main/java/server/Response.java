package server;

import java.io.IOException;

public class Response {
    private final String protocol;
    private final String statusCode;
    private final String headers;
    private final String body;

    public Response(String protocol, String statusCode, String headers, String body) {
        this.protocol = protocol;
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
    }

    public void write(ReadableWriteable readerWriter) throws IOException {
        readerWriter.writeLine(responseString());
    }

    private String responseString() {
        return this.protocol + " " + this.statusCode + "\r\n" + this.headers + "\r\n" + "\n" + this.body;
    }
}