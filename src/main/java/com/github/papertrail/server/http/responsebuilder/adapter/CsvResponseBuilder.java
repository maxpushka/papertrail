package com.github.papertrail.server.http.responsebuilder.adapter;

import com.github.papertrail.server.http.responsebuilder.ResponseBuilder;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CsvResponseBuilder implements ResponseBuilder {
    @Override
    public String build(List<JSONObject> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        JSONArray jsonArray = new JSONArray();
        for (JSONObject jsonObject : input) {
            if (!jsonObject.isEmpty()) {
                jsonArray.put(jsonObject);
            }
        }
        return CDL.toString(jsonArray);
    }
}
