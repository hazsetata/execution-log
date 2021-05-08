package com.hazse.executionlog.inmemory;

import com.hazse.executionlog.core.ILogStream;
import com.hazse.executionlog.core.ILogStreamElement;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogStream implements ILogStream {
    private final String id;
    private final ZonedDateTime creationTimeStamp;
    private final List<LogStreamElement> elements;

    public LogStream(String id, ZonedDateTime creationTimeStamp) {
        this.id = id;
        this.creationTimeStamp = creationTimeStamp;
        this.elements = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public ZonedDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    @Override
    public List<? extends ILogStreamElement> getElements() {
        return elements;
    }

    protected void addSection(LogStreamSection section) {
        elements.add(section);
    }

    protected boolean hasSection(LogStreamSection section) {
        return elements.contains(section);
    }

    protected void addEntry(LogStreamEntry entry) {
        elements.add(entry);
    }
}
