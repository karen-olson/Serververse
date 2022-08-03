package echoServer;

import java.io.IOException;

class EchoServer {

    private final Listener listener;

    public EchoServer(Listener listener) {
        this.listener = listener;
    }

    public void serve() throws IOException {
        Connection connection = listener.listen();
        String message = connection.readLine();
        connection.writeLine(message);
    }
}