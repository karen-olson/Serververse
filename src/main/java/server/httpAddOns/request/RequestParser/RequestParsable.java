package server.httpAddOns.request.RequestParser;

import server.basicServer.socketConnection.ReadableWriteable;
import server.httpAddOns.request.Request;

import java.io.IOException;

public interface RequestParsable {

    Request call(ReadableWriteable readableWriteable) throws IOException;
}
