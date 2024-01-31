package com.github.papertrail.server.http.querybuilder.adapter;

import com.github.papertrail.server.http.querybuilder.QueryBuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class SqlQueryBuilder implements QueryBuilder {
    @Override
    public String build(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
