package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SocketReaderWriterTest {

    @Test
    void readsALineOfText() throws IOException {
        TestSocket testSocket = new TestSocket("Hello\n");
        ReadableWriteable socketReaderWriter = new SocketReaderWriter(testSocket);

        String message = socketReaderWriter.readLine();

        assertEquals("Hello", message);
    }

    @Test
    void writesALineOfText() throws IOException {
        TestSocket testSocket = new TestSocket("");
        ReadableWriteable socketReaderWriter = new SocketReaderWriter(testSocket);

        socketReaderWriter.writeLine("Hello");

        String output = testSocket.output();

        assertEquals("Hello", output);
    }

    @Test
    void createsOnlyOneReaderAndOneWriter() throws IOException {
        TestSocket testSocket = new TestSocket(
                """
                        First input line
                        Second input line
                        """
        );
        ReadableWriteable socketReaderWriter = new SocketReaderWriter(testSocket);

        socketReaderWriter.readLine();
        socketReaderWriter.readLine();
        socketReaderWriter.writeLine("");
        socketReaderWriter.writeLine("");

        assertEquals(List.of(
                "A new reader accessed the input stream",
                "A new writer accessed the output stream"
        ), testSocket.events());
    }

    @Test
    void closesTheConnection() throws Exception {
        TestSocket testSocket = new TestSocket("Hello\n");
        ReadableWriteable socketReaderWriter = new SocketReaderWriter(testSocket);

        socketReaderWriter.close();

        assertEquals(List.of(
                "A new reader accessed the input stream",
                "A new writer accessed the output stream",
                "The socket has been closed"
        ), testSocket.events());
    }
}