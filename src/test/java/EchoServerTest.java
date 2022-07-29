import interfaces.IServerSocket;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Main class.
 */
public class EchoServerTest {

    @Test
    void createsServerSocket() {
        IServerSocket testServerSocket = new TestServerSocket();
        EchoServer echoServer = new EchoServer(testServerSocket);

        echoServer.main();

        assertEquals(List.of("Server socket created!"), testServerSocket.messages());
    }
}

class TestServerSocket implements IServerSocket {
    List<String> messages = List.of("Server socket created!");

    public List<String> messages() {
        return messages;
    }
}
