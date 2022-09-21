package server;

public class ResponseWriter implements ResponseWriteable {

    String CRLF = "\r\n";

    public void call(ReadableWriteable readerWriter, Response response) {
        readerWriter.writeLine(format(response));
    }

    private String format(Response response) {
        String statusLine = statusLine(response);
        String headers = headers(response);
        String body = response.body();

        return statusLine + headers + CRLF + body;
    }

    private String statusLine(Response response) {
        String space = " ";
        return response.protocol() + space + response.statusCode() + CRLF;
    }

    private String headers(Response response) {
        StringBuilder headers = new StringBuilder();
        for (String key : response.headers().keySet()) {
            headers.append(key).append(": ").append(response.headers().get(key)).append(CRLF);
        }
        return headers.toString();
    }
}
