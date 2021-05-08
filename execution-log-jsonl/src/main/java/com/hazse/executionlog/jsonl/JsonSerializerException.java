package com.hazse.executionlog.jsonl;

public class JsonSerializerException extends RuntimeException {
    public JsonSerializerException(String message) {
        super(message);
    }

    public JsonSerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
