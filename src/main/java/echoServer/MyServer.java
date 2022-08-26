package echoServer;

public class MyServer {

    private final Loopable looper;
    private final PortListenable portListener;

    public MyServer(Loopable looper, PortListenable portListener) {
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