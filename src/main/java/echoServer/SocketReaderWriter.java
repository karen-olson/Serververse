package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketReaderWriter implements ReadableWriteable {
    private final Socket socket;

    public SocketReaderWriter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String readLine() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream())
        );
        return reader.readLine();
    }

    @Override
    public void writeLine(String message) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(message);
    }
}
