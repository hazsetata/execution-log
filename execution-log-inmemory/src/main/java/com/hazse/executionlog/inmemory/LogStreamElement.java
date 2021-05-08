package com.hazse.executionlog.inmemory;

import com.hazse.executionlog.core.ILogStreamElement;

public class LogStreamElement implements ILogStreamElement {
    protected final long sequenceNumber;

    public LogStreamElement(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }
}
