package server;

public class ResponseWriter implements ResponseWriteable {

    String CRLF = "\r\n";

    public void call(ReadableWriteable readerWriter, Response response) {
        readerWriter.writeLine(format(response));
    }

    private String format(Response response) {
        String space = " ";
        String headers = headersAsString(response);

        return response.protocol() + space + response.statusCode() + CRLF +
                headers + CRLF +
                response.body();
    }

    private String headersAsString(Response response) {
        StringBuilder headersAsString = new StringBuilder();
        for (String key : response.headers().keySet()) {
            headersAsString.append(key + ": " + response.headers().get(key) + CRLF);
        }
        return headersAsString.toString();
    }
}
