package com.hazse.executionlog.inmemory;

import com.hazse.executionlog.core.ILogStreamEntry;
import com.hazse.executionlog.core.LogChannel;

import java.time.ZonedDateTime;
import java.util.Objects;

public class LogStreamEntry extends LogStreamElement implements ILogStreamEntry {
    private final long ownerSectionSequenceNumber;
    private final ZonedDateTime timestamp;
    private final LogChannel channel;
    private final String text;

    public LogStreamEntry(long sequenceNumber, ZonedDateTime timestamp, LogStreamSection owner, LogChannel channel, String text) {
        super(sequenceNumber);
        this.ownerSectionSequenceNumber = owner.getSequenceNumber();

        this.timestamp = timestamp;
        this.channel = channel;
        this.text = text;
    }

    @Override
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public long getOwnerSectionSequenceNumber() {
        return ownerSectionSequenceNumber;
    }

    @Override
    public LogChannel getChannel() {
        return channel;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogStreamEntry that = (LogStreamEntry) o;
        return sequenceNumber == that.sequenceNumber && ownerSectionSequenceNumber == that.ownerSectionSequenceNumber && timestamp.equals(that.timestamp) && channel == that.channel && text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber, ownerSectionSequenceNumber, timestamp, channel, text);
    }

    @Override
    public String toString() {
        return "LogStreamEntry{" +
                "sequenceNumber=" + sequenceNumber +
                ", ownerSectionSequenceNumber=" + ownerSectionSequenceNumber +
                ", timestamp=" + timestamp +
                ", channel=" + channel +
                ", text='" + text + '\'' +
                '}';
    }
}
