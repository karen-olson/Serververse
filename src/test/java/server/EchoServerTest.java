package server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoServerTest {

    @Test
    void itEchoesSentText() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter().send("hello\n");

        new EchoServer().call(testReaderWriter);

        assertEquals(List.of("hello\n"), testReaderWriter.received());
    }

    @Test
    void itEchoesMultipleLinesOfSentText() throws IOException {
        TestReaderWriter testReaderWriter = new TestReaderWriter()
                .send("first line\n")
                .send("second line\n");

        new EchoServer().call(testReaderWriter);

        assertEquals(List.of(
                        "first line\n",
                        "second line\n"),
                testReaderWriter.received());
    }
}