package echoServer;

public class EchoServer {

    private final PortListenable portListener;
    private final Loopable looper;

    public EchoServer(PortListenable portListener, Loopable looper) {
        this.portListener = portListener;
        this.looper = looper;
    }

    public void serve() throws Exception {
        looper.loop(() -> {
            ReadableWriteable socketReaderWriter = portListener.listen();

            String message;
            while ((message = socketReaderWriter.readLine()) != null) {
                socketReaderWriter.writeLine(message);
            }

            socketReaderWriter.close();
        });
    }
}