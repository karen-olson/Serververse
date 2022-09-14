package server;

public class Server {

    private final Loopable looper;
    private final PortListenable portListener;

    public Server(Loopable looper, PortListenable portListener) {
        this.looper = looper;
        this.portListener = portListener;
    }

    public void serve(Application app) throws Exception {
        looper.loop(() -> {
            try (ReadableWriteable readerWriter = portListener.listen()) {
                app.call(readerWriter);
            }
        });
    }
}