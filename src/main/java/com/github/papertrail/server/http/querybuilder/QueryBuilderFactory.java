package com.github.papertrail.server.http.querybuilder;

import com.github.papertrail.server.http.querybuilder.adapter.*;

public class QueryBuilderFactory {
    public static QueryBuilder build(String contentTypeHeader) throws IllegalArgumentException {
        return switch (contentTypeHeader) {
            case "application/json" -> new JsonQueryBuilder();
            case "application/ld+json" -> new LdJsonQueryBuilder();
            case "application/sql" -> new SqlQueryBuilder();
            case "multipart/form-data" -> new DataQueryBuilder();
            case "application/x-www-form-urlencoded" -> new UrlEncodedQueryBuilder();
            case null -> new SqlQueryBuilder();
            default -> throw new IllegalArgumentException("Header " + contentTypeHeader + " is not supported");
        };
    }
}
