package com.hazse.executionlog.inmemory;

import com.hazse.executionlog.core.ILogger;
import com.hazse.executionlog.core.LogChannel;
import com.hazse.executionlog.core.exception.ClosedLoggerException;
import com.hazse.executionlog.core.exception.LogStructureException;

import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Logger implements ILogger<LogStreamSection, LogStreamEntry> {
    private final LogStream log;

    private LogStreamSection defaultSection;
    private boolean closed;
    private AtomicLong sequenceCounter = new AtomicLong(0);

    public Logger(LogStream log) {
        this.log = log;
        this.defaultSection = null;
        this.closed = false;
    }

    @Override
    public LogStreamSection createSection(String title, boolean setAsDefault) {
        if (!closed) {
            LogStreamSection retValue = new LogStreamSection(sequenceCounter.getAndIncrement(), title);
            log.addSection(retValue);

            if (setAsDefault) {
                defaultSection = retValue;
            }

            return retValue;
        }
        else {
            throw new ClosedLoggerException();
        }
    }

    @Override
    public LogStreamSection getDefaultSection() {
        return defaultSection;
    }

    @Override
    public LogStreamEntry createEntry(String text, LogChannel channel, LogStreamSection owner) {
        if (!closed) {
            if (owner == null) {
                throw new LogStructureException("Entry can't be added to a null section.");
            }
            else if (log.hasSection(owner)) {
                LogStreamEntry retValue = new LogStreamEntry(sequenceCounter.getAndIncrement(), ZonedDateTime.now(), owner, channel, text);
                log.addEntry(retValue);

                return retValue;
            }
            else {
                throw new LogStructureException("The specified section doesn't belong to the log managed by this logger.");
            }
        }
        else {
            throw new ClosedLoggerException();
        }
    }

    @Override
    public void close() {
        closed = true;
    }
}
