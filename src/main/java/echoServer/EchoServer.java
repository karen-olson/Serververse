package echoServer;

import java.io.IOException;

public class EchoServer {
    IServerSocket serverSocket;
    IClientSocket clientSocket;
    IReader reader;
    IWriter writer;

    public EchoServer(IServerSocket serverSocket, IClientSocket clientSocket, IReader reader, IWriter writer) {
        this.serverSocket = serverSocket;
        this.clientSocket = clientSocket;
        this.reader = reader;
        this.writer = writer;
    }

    public void main() throws IOException {
        String text = reader.readLine();
        writer.println(text);

        reader.closeReader();
        writer.closeWriter();
        
        clientSocket.closeClientConnection();
        serverSocket.closeServerConnection();
    }
}