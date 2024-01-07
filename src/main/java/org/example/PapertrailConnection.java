package org.example;

import com.google.gson.Gson;

import java.util.List;

public interface PapertrailConnection {
    List<Gson> query(String query);

    int exec(String query);
}
