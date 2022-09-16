package server;

public interface ResponseWriteable {

    void call(ReadableWriteable readerWriter, Response response);
}
