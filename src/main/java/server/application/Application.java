package server.application;

import server.basicServer.socketConnection.ReadableWriteable;

import java.io.IOException;

public interface Application {
    void call(ReadableWriteable readerWriter) throws IOException;
}
