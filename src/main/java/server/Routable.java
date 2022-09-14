package server;

public interface Routable {
    Response call(Request request);
}
