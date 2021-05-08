package com.hazse.executionlog.inmemory;

import com.hazse.executionlog.core.ILogStreamSection;

import java.util.Objects;

public class LogStreamSection extends LogStreamElement implements ILogStreamSection {
    private final String title;

    public LogStreamSection(long sequenceNumber, String title) {
        super(sequenceNumber);
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogStreamSection that = (LogStreamSection) o;
        return sequenceNumber == that.sequenceNumber && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber, title);
    }

    @Override
    public String toString() {
        return "LogStreamSection{" +
                "sequenceNumber=" + sequenceNumber +
                ", title='" + title + '\'' +
                '}';
    }
}
