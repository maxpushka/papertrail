package com.github.papertrail.contenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentType {
    JSON("application/json"),
    LD_JSON("application/ld+json"),
    SQL("application/sql");

    private final String contentType;
}
