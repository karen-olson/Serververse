package server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    @Test
    void itGivesApplicationASocketConnection() throws Exception {
        TestApplication app = new TestApplication();
        Loopable doItOnce = new DoItOnce();
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        TestPortListener testPortListener = new TestPortListener(testReaderWriter);

        new Server(doItOnce, testPortListener).serve(app);

        assertEquals(List.of(
                "App was called",
                "New socket connection received"
        ), app.events());
    }

    @Test
    void itClosesTheConnection() throws Exception {
        TestApplication app = new TestApplication();
        Loopable doItOnce = new DoItOnce();
        ReaderWriterLog readerWriterLog = new ReaderWriterLog();
        TestPortListener testPortListener = new TestPortListener(readerWriterLog);

        new Server(doItOnce, testPortListener).serve(app);

        assertEquals(List.of(
                "Connection closed"
        ), readerWriterLog.events());
    }

    @Test
    void itAcceptsRepeatedConnections() throws Exception {
        TestApplication app = new TestApplication();
        Loopable doItTwice = new DoItTwice();
        TestReaderWriter testReaderWriter = new TestReaderWriter();
        TestPortListener testPortListener = new TestPortListener(testReaderWriter);

        new Server(doItTwice, testPortListener).serve(app);

        assertEquals(List.of(
                "App was called",
                "New socket connection received",
                "App was called",
                "New socket connection received"
        ), app.events());
    }

    private static class TestApplication implements Application {
        private final List<String> events = new ArrayList<>();

        public List<String> events() {
            return events;
        }

        @Override
        public void call(ReadableWriteable readerWriter) {
            events.add("App was called");
            if (readerWriter != null) events.add("New socket connection received");
        }
    }

    private static class DoItOnce implements Loopable {
        @Override
        public void loop(Block block) throws Exception {
            block.call();
        }
    }

    private static class DoItTwice implements Loopable {
        @Override
        public void loop(Block block) throws Exception {
            block.call();
            block.call();
        }
    }

    private static class ReaderWriterLog implements ReadableWriteable {
        private final List<String> events = new ArrayList<>();

        @Override
        public String readLine() {
            return null;
        }

        @Override
        public void writeLine(String message) {
        }

        @Override
        public void close() {
            events.add("Connection closed");
        }

        public List<String> events() {
            return events;
        }
    }

    private record TestPortListener(ReadableWriteable testReaderWriter) implements PortListenable {

        @Override
        public ReadableWriteable listen() {
            return testReaderWriter;
        }
    }
}