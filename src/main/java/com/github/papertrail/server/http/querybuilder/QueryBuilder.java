package com.github.papertrail.server.http.querybuilder;


import jakarta.servlet.http.HttpServletRequest;

public interface QueryBuilder {
    String build(HttpServletRequest request);
}
