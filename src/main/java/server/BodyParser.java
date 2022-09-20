package server;

import java.io.IOException;

public class BodyParser {

    public String parse(ReadableWriteable readerWriter, int contentLength) throws IOException {
        String body = "";
        while (body.length() < contentLength) {
            body += readerWriter.readLine();
        }
        return body;
    }
}
