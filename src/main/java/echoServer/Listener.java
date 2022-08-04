package echoServer;

import java.io.IOException;

public interface Listener {
    Connection listen() throws IOException;
}
