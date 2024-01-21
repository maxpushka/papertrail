package com.github.papertrail.driver;

import com.github.papertrail.parser.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.*;

public class MockDriver implements Driver {
    @Override
    public List<JsonElement> run(Transaction tx) {
        final String jsonString = "{\"name\": \"hello\", \"text\": \"world\"}";
        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        return Collections.singletonList(jsonObject);
    }
}
