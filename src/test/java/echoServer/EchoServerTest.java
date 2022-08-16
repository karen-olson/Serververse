package echoServer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EchoServerTest {
    @Test
    public void itEchoesSentText() throws Exception {
        TestReaderWriter testReaderWriter = new TestReaderWriter().send("hello\n");
        TestListener testListener = new TestListener(testReaderWriter);
        Loopable looper = new DoItOnce();
        new EchoServer(testListener, looper).serve();

        assertTrue(testReaderWriter.received("hello\n"));
    }

    @Test
    public void itEchoesMultipleLinesOfSentText() throws Exception {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("first line\n")
                .send("second line\n");

        TestListener testListener = new TestListener(testReaderWriter);
        Loopable looper = new DoItOnce();
        new EchoServer(testListener, looper).serve();

        assertEquals(List.of(
                        "first line\n",
                        "second line\n"),
                testReaderWriter.received());
    }

    @Test
    public void closesIOStreamsAfterDisconnecting() throws Exception {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("text sent when connection is open\n")
                .closeConnection();

        TestListener testListener = new TestListener(testReaderWriter);
        Loopable looper = new DoItOnce();
        new EchoServer(testListener, looper).serve();

        assertEquals(List.of(
                        "Closed ReadableWriteable IO streams"),
                testReaderWriter.events());
    }

    @Test
    public void itAcceptsRepeatedConnections() throws Exception {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("text sent over first connection\n")
                .closeConnection()
                .send("text sent over second connection \n");

        TestListener testListener = new TestListener(testReaderWriter);
        Loopable looper = new DoItTwice();
        new EchoServer(testListener, looper).serve();

        assertEquals(List.of(
                        "text sent over first connection\n",
                        "text sent over second connection \n"),
                testReaderWriter.received());
    }

    private static class TestReaderWriter implements ReadableWriteable {

        private final List<String> toRead = new ArrayList<>();
        private final List<String> written = new ArrayList<>();
        private final List<String> events = new ArrayList<>();

        public TestReaderWriter send(String message) {
            toRead.add(message);
            return this;
        }

        public boolean received(String message) {
            return written.contains(message);
        }

        public List<String> received() {
            return written;
        }

        public List<String> events() {
            return events;
        }

        public String readLine() {
            if (toRead.isEmpty()) {
                return null;
            }
            return toRead.remove(0);
        }

        public void writeLine(String message) {
            written.add(message);
        }

        @Override
        public void close() {
            events.add("Closed ReadableWriteable IO streams");
        }

        public TestReaderWriter closeConnection() {
            return this.send(null);
        }
    }

    private record TestListener(TestReaderWriter testReaderWriter) implements PortListenable {

        @Override
        public ReadableWriteable listen() {
            return this.testReaderWriter;
        }
    }

    private class DoItOnce implements Loopable {

        public void loop(Block block) throws Exception {
            block.call();
        }
    }

    private class DoItTwice implements Loopable {

        public void loop(Block block) throws Exception {
            block.call();
            block.call();
        }
    }
}