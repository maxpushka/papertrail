package com.github.papertrail.contenttype;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentType {
    JSON("application/json"),
    LD_JSON("application/ld+json"),
    SQL("application/sql"),
    FORM_DATA("multipart/form-data"),
    FORM_URLENCODED("application/x-www-form-urlencoded");

    private final String contentType;
}
