package com.github.papertrail.contenttype;

public class ContentTypeValidator {
    public ContentType isValid(String value) {
        for (ContentType contentType : ContentType.values()) {
            if (contentType.getContentType().equals(value)) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("This content-Type doesn't exist");
    }
}
