package echoServer;

public interface Connection {
    String readLine();

    void writeLine(String message);
}
