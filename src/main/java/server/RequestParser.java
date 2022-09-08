package server;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    private final String CRLF = "\r\n";

    public Request call(String rawRequest) {
        String requestLine = getRequestLine(rawRequest);
        Map<String, String> headers = getHeaders(rawRequest);
        String body = getBody(rawRequest);

        return new Request(
                requestLine,
                headers,
                body
        );
    }

    private String getRequestLine(String rawRequest) {
        Integer startIndex = 0;
        Integer endIndex = rawRequest.indexOf(CRLF);

        return rawRequest.substring(startIndex, endIndex);
    }

    private HashMap<String, String> getHeaders(String rawRequest) {
        String headersString = getHeadersString(rawRequest);
        String[] splitHeaders = headersString.split("\\r\\n");

        return populateHeaders(splitHeaders);
    }

    private String getHeadersString(String rawRequest) {
        Integer startIndex = rawRequest.indexOf(CRLF) + CRLF.length();
        Integer endIndex = rawRequest.indexOf(CRLF + CRLF);

        return rawRequest.substring(startIndex, endIndex);
    }

    private HashMap<String, String> populateHeaders(String[] splitHeaders) {
        HashMap<String, String> headers = new HashMap<>();
        for (int i = 0; i < splitHeaders.length; i++) {
            String[] splitHeader = splitHeaders[i].split(":");
            headers.put(splitHeader[0], splitHeader[1]);
        }
        return headers;
    }

    private String getBody(String rawRequest) {
        Integer startIndex = rawRequest.indexOf(CRLF + CRLF) + (CRLF + CRLF).length();

        return rawRequest.substring(startIndex);
    }
}
