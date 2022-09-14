package server;

import java.io.IOException;

public interface ResponseWriteable {

    void call(ReadableWriteable readerWriter, Response response) throws IOException;
}
