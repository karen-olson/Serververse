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
    public String readAll() throws IOException {
        int character;
        StringBuilder request = new StringBuilder();

        while ((character = reader.read()) != -1) {
            request.append((char) character);
            System.out.println("character: " + character);
        }

        System.out.println("request: " + request);

        return request.toString();

//        char[] buff = new char[1024];
//        int read;
//        StringBuilder request = new StringBuilder();
//        while ((read = reader.read(buff)) != (-1)) {
//            request.append(buff, 0, read);
//            System.out.println("read: " + read);
//            System.out.println("buff lengths: " + buff.length);
//        }
//
//        return request.toString();
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
