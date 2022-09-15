package server;

public interface Handler {
    Response call(Request request);
}
