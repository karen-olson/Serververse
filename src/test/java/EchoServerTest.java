import echoServer.EchoServer;
import echoServer.IClientSocket;
import echoServer.IReader;
import echoServer.IServerSocket;
import echoServer.IWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the echoServer.EchoServer class.
 */
public class EchoServerTest {

    @Test
    void echoesALineOfText() throws IOException {
        EventLog eventLog = new EventLog();
        EchoServer echoServer = new EchoServer(eventLog, eventLog, eventLog, eventLog);

        echoServer.main();

        ArrayList expected_events = new ArrayList<String>(
                Arrays.asList(
                        "Read client input",
                        "Echoed output to client",
                        "Closed input stream",
                        "Closed output stream",
                        "Closed client connection",
                        "Closed server connection"
                ));

        assertEquals(expected_events, EventLog.events());
    }
}

class EventLog implements IServerSocket, IClientSocket, IReader, IWriter {
    static List<String> events = new ArrayList<String>();

    public static List<String> events() {
        return events;
    }

    public String readLine() {
        events.add("Read client input");
        return null;
    }

    public void println(String s) {
        events.add("Echoed output to client");
    }

    public void closeReader() {
        events.add("Closed input stream");
    }

    public void closeWriter() {
        events.add("Closed output stream");
    }

    public void closeClientConnection() {
        events.add("Closed client connection");
    }

    public void closeServerConnection() {
        events.add("Closed server connection");
    }
}
