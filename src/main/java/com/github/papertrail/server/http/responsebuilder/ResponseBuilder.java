package com.github.papertrail.server.http.responsebuilder;

import com.google.gson.JsonElement;

import java.util.List;

public interface ResponseBuilder {
    String build(List<JsonElement> input);
}
