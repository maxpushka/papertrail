package org.example;

import com.google.gson.Gson;

public interface PaperTrailConnection {
    Gson query(String query);

    int exec(String query);
}
