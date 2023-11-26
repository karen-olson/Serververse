package server;

import java.io.IOException;

public class BodyParser {
    public String parse(ReadableWriteable readerWriter, int contentLength) throws IOException {
        return readerWriter.read(contentLength);
    }
}
