package server.httpAddOns.response.ResponseWriter;

import server.basicServer.socketConnection.ReadableWriteable;
import server.httpAddOns.response.Response;

public interface ResponseWriteable {

    void call(ReadableWriteable readerWriter, Response response);
}
