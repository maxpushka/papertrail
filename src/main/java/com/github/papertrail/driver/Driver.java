package com.github.papertrail.driver;

import java.util.List;

import com.github.papertrail.parser.Transaction;
import com.google.gson.JsonElement;

public interface Driver {
    List<JsonElement> run(Transaction tx);
}
