package server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RequestLineParser {

    public RequestLine parse(ReadableWriteable readableWriteable) throws IOException {
        String line = readableWriteable.readLine();
        System.out.println("line in RequestLineParser: " + line);

        List<String> rawRequestLine = Arrays.asList(line.split("\\s"));
        System.out.println("raw request line: " + rawRequestLine);

        String method = rawRequestLine.get(0);
        String path = rawRequestLine.get(1);

        RequestLine requestLine = new RequestLine(method, path);
        System.out.println("request line object:" + requestLine);

        return requestLine;
    }

    public record RequestLine(String method, String path) {
    }
}
