package com.hazse.executionlog.core.exception;

public class ClosedLoggerException extends LoggerException{
    public ClosedLoggerException() {
        super("Logger is already closed.");
    }
}
