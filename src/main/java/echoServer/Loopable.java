package echoServer;

public interface Loopable {
    void loop(Block block) throws Exception;

    interface Block {
        void call() throws Exception;
    }
}

