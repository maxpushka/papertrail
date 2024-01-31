package com.github.papertrail;

import org.json.JSONArray;

public interface PapertrailConnection {
    JSONArray query(String query);

    int exec(String query);
}
