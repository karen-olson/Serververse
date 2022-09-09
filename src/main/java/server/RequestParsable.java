package server;

public interface RequestParsable {

    Request call(String rawRequest);
}
