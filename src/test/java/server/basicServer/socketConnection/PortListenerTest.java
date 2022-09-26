package server.basicServer.socketConnection;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PortListenerTest {

    @Test
    void itListensAndConnectsToASocket() throws Exception {
        TestServerSocket testServerSocket = new TestServerSocket("From test server socket");
        PortListenable portListener = new PortListener(testServerSocket);

        try (ReadableWriteable socketReaderWriter = portListener.listen()) {

            assertEquals("From test server socket", socketReaderWriter.readLine());
        }
    }

    private static class TestServerSocket extends ServerSocket {

        private final String stringToAssertAbout;

        public TestServerSocket(String stringToAssertAbout) throws IOException {
            this.stringToAssertAbout = stringToAssertAbout;
        }

        @Override
        public Socket accept() {
            return new TestSocket(stringToAssertAbout);
        }
    }
}
