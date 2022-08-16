package echoServer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertEquals("Hello\n", output);
    }

    @Test
    void itCloses() throws IOException {
        TestSocket testSocket = new TestSocket("Hello\n");
        ReadableWriteable socketReaderWriter = new SocketReaderWriter(testSocket);

        socketReaderWriter.readLine();
        socketReaderWriter.writeLine("Hello");

        socketReaderWriter.close();

        assertThrows(IOException.class, () -> {
            socketReaderWriter.readLine();
        });
    }
}