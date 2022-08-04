package echoServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerSocketListenerTest {

    @Test
    void itListensAndMakesAConnection() throws IOException {
        Listener listener = new ServerSocketListener(new TestServerSocket("From test server socket"));

        Connection connection = listener.listen();

        assertEquals("From test server socket", connection.readLine());
    }

    private class ServerSocketListener implements Listener {

        private final ServerSocket serverSocket;

        public ServerSocketListener(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        @Override
        public Connection listen() throws IOException {
            return new SocketConnection(serverSocket.accept());
        }
    }

    private class TestServerSocket extends ServerSocket {

        private final String stringToAssertAbout;

        public TestServerSocket(String stringToAssertAbout) throws IOException {
            this.stringToAssertAbout = stringToAssertAbout;
        }

        @Override
        public Socket accept() throws IOException {
            return new TestSocket(stringToAssertAbout);
        }
    }
}
