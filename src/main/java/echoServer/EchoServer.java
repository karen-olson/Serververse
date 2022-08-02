package echoServer;

import java.io.IOException;

public class EchoServer {
    PortListener portListener;
    Connection connection;
    IReader reader;
    IWriter writer;

    public EchoServer(PortListener portListener, Connection connection, IReader reader, IWriter writer) {
        this.portListener = portListener;
        this.connection = connection;
        this.reader = reader;
        this.writer = writer;
    }

    public void main() throws IOException {
        String text = reader.readLine();
        writer.println(text);

        reader.closeReader();
        writer.closeWriter();

        connection.closeConnection();
        portListener.stopListening();
    }
}