package org.example;

import com.google.gson.Gson;

import java.net.URI;

public class Papertrail implements Connection {
    public Papertrail(URI uri) {

    }

    @Override
    public Gson query() {
        return null;
    }

    @Override
    public int exec() {
        return 0;
    }
}
