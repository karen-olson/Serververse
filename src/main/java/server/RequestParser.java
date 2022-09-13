package server;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private final String lineSeparator = "\r\n";

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
        int startIndex = 0;
        int endIndex = rawRequest.indexOf(lineSeparator);

        return rawRequest.substring(startIndex, endIndex);
    }

    private HashMap<String, String> getHeaders(String rawRequest) {
        String headersString = getHeadersString(rawRequest);
        String escapedLineSeparator = "\\r\\n";
        String[] splitHeaders = headersString.split(escapedLineSeparator);

        return populateHeaders(splitHeaders);
    }

    private String getHeadersString(String rawRequest) {
        int startIndex = rawRequest.indexOf(lineSeparator) + lineSeparator.length();
        int endIndex = rawRequest.indexOf(lineSeparator + lineSeparator);

        return rawRequest.substring(startIndex, endIndex);
    }

    private HashMap<String, String> populateHeaders(String[] splitHeaders) {
        HashMap<String, String> headers = new HashMap<>();
        for (String header : splitHeaders) {
            String[] headerElements = header.split(":");
            headers.put(headerElements[0], headerElements[1]);
        }
        return headers;
    }

    private String getBody(String rawRequest) {
        String headersEndSeparator = "\r\n\r\n";
        int startIndex = rawRequest.indexOf(headersEndSeparator) + (headersEndSeparator).length();

        return rawRequest.substring(startIndex);
    }
}
