package com.github.papertrail.server.http.querybuilder.adapter;

import com.github.papertrail.server.http.querybuilder.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class JsonQueryBuilder implements QueryBuilder {
    @Override
    public String build(HttpServletRequest requestBody) {
        StringBuilder queryBuilder = new StringBuilder();

        try (BufferedReader reader = requestBody.getReader()) {
            JSONObject jsonObject = new JSONObject(reader.readLine());

            if (!jsonObject.has("q")) {
                throw new IllegalArgumentException("Query 'q' key not found in JSON.");
            }

            String query = jsonObject.getString("q");

            if (jsonObject.has("p")) {
                Object params = jsonObject.get("p");
                if (params instanceof JSONArray) {
                    JSONArray paramArray = (JSONArray) params;
                    for (Object param : paramArray) {
                        query = replaceFirstParameterPlaceholder(query, param.toString());
                    }
                } else if (params instanceof JSONObject) {
                    JSONObject paramMap = (JSONObject) params;
                    for (Map.Entry<String, Object> entry : paramMap.toMap().entrySet()) {
                        query = replaceNamedParameter(query, entry.getKey(), entry.getValue().toString());
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported parameter type.");
                }
            }

            queryBuilder.append(query);

        } catch (IOException | JSONException e) {
            throw new RuntimeException("Error parsing JSON or reading request.", e);
        }

        return queryBuilder.toString();
    }

    private String replaceFirstParameterPlaceholder(String query, String param) {
        return query.replaceFirst("\\?", "'" + param + "'");
    }

    private String replaceNamedParameter(String query, String paramName, String paramValue) {
        return query.replaceAll(":" + paramName, "'" + paramValue + "'");
    }
}
