package com.github.papertrail.driver;

import com.github.papertrail.parser.Transaction;
import org.json.JSONObject;

import java.util.*;

public class MockDriver implements Driver {
    @Override
    public List<JSONObject> run(Transaction tx) {
        JSONObject jsonObject = new JSONObject();
        return Collections.singletonList(jsonObject);
    }
}
