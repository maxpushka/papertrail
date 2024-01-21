package com.github.papertrail.parser;

import com.github.papertrail.parser.statement.Insert;
import com.github.papertrail.parser.statement.Select;

public class MockParser {
    public Transaction parse(String query) {
        var tx = new Transaction();
        tx.statements.add(new Insert());
        tx.statements.add(new Select());

        return tx;
    }
}
