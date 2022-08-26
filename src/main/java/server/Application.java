package server;

import java.io.IOException;

public interface Application {
    void call(ReadableWriteable readerWriter) throws IOException;
}
