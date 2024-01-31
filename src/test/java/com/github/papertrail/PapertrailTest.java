package com.github.papertrail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PapertrailTest {

    @Test
    void query() throws URISyntaxException {
        URI uri = new URI("");
        Assertions.assertNull(new Papertrail(uri).query(""));
    }

    @Test
    void exec() throws URISyntaxException {
        URI uri = new URI("");
        assertEquals(0, new Papertrail(uri).exec(""));
    }
}
