package com.github.papertrail.server.http.responsebuilder;
import com.github.papertrail.server.http.responsebuilder.adapter.*;

public class ResponseBuilderFactory {
    public static ResponseBuilder build(String acceptHeader) throws IllegalArgumentException {

        return switch (acceptHeader) {
            case "text/csv" -> new CsvResponseBuilder();
            case "application/json" -> new JsonResponseBuilder();
            case "application/x-ndjson" -> new XNdJsonResponseBuilder();
            case "application/ld+json" -> new LdJsonResponseBuilder();
            case "application/vnd.apache.arrow.file" -> new ApacheArrowFileResponseBuilder();
            case "application/vnd.apache.arrow.stream" -> new ApacheArrowStreamResponseBuilder();
            case null -> new JsonResponseBuilder();
            default -> throw new IllegalArgumentException("Header " + acceptHeader + " is not supported");
        };
    }
}
