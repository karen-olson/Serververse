package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketReaderWriter implements ReadableWriteable {
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public SocketReaderWriter(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream(), StandardCharsets.UTF_8)
        );
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public String read(int contentLength) throws IOException {
        char[] container = new char[contentLength];
        //noinspection ResultOfMethodCallIgnored
        reader.read(container, 0, contentLength);
        return new String(container, 0, contentLength);
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }

    @Override
    public void writeLine(String message) {
        writer.print(message);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
