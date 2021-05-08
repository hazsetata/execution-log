package com.hazse.executionlog.core.exception;

public class LoggerException extends RuntimeException{
    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(String message, Throwable cause) {
        super(message, cause);
    }
}
