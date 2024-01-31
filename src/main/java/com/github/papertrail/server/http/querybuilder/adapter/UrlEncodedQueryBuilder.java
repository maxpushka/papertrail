package com.github.papertrail.server.http.querybuilder.adapter;

import com.github.papertrail.server.http.querybuilder.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;

public class UrlEncodedQueryBuilder implements QueryBuilder {
    @Override
    public String build(HttpServletRequest requestBody) {
        return null;
    }
}
