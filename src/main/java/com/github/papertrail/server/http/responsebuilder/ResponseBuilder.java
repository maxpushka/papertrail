package com.github.papertrail.server.http.responsebuilder;

import org.json.JSONObject;

import java.util.List;

public interface ResponseBuilder {
    String build(List<JSONObject> input);
}
