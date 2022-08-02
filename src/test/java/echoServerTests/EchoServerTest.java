package echoServerTests;

import echoServer.Connection;
import echoServer.EchoServer;
import echoServer.IReader;
import echoServer.IWriter;
import echoServer.PortListener;
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
                        "Closed connection",
                        "Stopped listening"
                ));

        assertEquals(expected_events, EventLog.events());
    }


}

class EventLog implements PortListener, Connection, IReader, IWriter {
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

    @Override
    public void closeConnection() {
        events.add("Closed connection");
    }

    @Override
    public void stopListening() {
        events.add("Stopped listening");
    }
}
