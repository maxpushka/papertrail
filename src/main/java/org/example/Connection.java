package org.example;

import com.google.gson.Gson;

public interface Connection {
    Gson query();

    int exec();
}
