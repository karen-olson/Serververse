package echoServer;

import java.io.IOException;

public interface PortListener {

    void stopListening() throws IOException;
}
