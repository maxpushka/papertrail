package org.example;

import com.google.gson.Gson;

public interface Connection {
    Gson query(String query);

    int exec(String query);
}
