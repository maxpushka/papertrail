package com.github.papertrail.server.http.responsebuilder.adapter;

import com.github.papertrail.server.http.responsebuilder.ResponseBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.List;

public class JsonResponseBuilder implements ResponseBuilder {
    @Override
    public String build(List<JsonElement> input) {
        if (input == null || input.isEmpty()) {
            return "[]";
        }

        JsonArray jsonArray = new JsonArray();
        for (JsonElement jsonElement : input) {
            if (jsonElement.isJsonArray()) {
                jsonArray.add(jsonElement.getAsJsonArray());
            }
        }
        return jsonArray.toString();
    }
}
