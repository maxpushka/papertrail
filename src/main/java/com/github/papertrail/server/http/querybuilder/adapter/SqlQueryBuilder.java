package com.github.papertrail.server.http.querybuilder.adapter;

import com.github.papertrail.server.http.querybuilder.QueryBuilder;

import jakarta.servlet.http.HttpServletRequest;

public class SqlQueryBuilder implements QueryBuilder {
    @Override
    public String build(HttpServletRequest requestObject) {
        return null;
    }
}