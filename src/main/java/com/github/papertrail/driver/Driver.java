package com.github.papertrail.driver;

import java.util.List;

import com.github.papertrail.parser.Transaction;
import org.json.JSONObject;


public interface Driver {
    List<JSONObject> run(Transaction tx);
}
