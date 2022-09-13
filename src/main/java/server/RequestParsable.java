package server;

import java.io.IOException;

public interface RequestParsable {

    NewRequest call(ReadableWriteable readableWriteable) throws IOException;
}
