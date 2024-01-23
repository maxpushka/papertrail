package com.github.papertrail.server.http.querybuilder.adapter;

import com.github.papertrail.server.http.querybuilder.QueryBuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class SqlQueryBuilder implements QueryBuilder {
    @Override
    public String build(HttpServletRequest requestObject) {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = requestObject.getReader()) {
            String line;

            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            return requestBody.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
