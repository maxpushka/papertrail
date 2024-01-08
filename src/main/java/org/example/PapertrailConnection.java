package org.example;

import com.google.gson.JsonArray;

public interface PapertrailConnection {
    JsonArray query(String query);

    int exec(String query);
}
