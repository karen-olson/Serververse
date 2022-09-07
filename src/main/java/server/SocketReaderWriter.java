package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketReaderWriter implements ReadableWriteable {
    private final Socket socket;
    private final BufferedReader reader;
    private final PrintWriter writer;

    public SocketReaderWriter(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
        );
        this.writer = new PrintWriter(socket.getOutputStream(), true);
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
