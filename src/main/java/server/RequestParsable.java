package server;

import java.io.IOException;

public interface RequestParsable {

    Request call(ReadableWriteable readableWriteable) throws IOException;
}
