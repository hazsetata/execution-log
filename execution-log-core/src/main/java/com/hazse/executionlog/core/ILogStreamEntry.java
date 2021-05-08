package com.hazse.executionlog.core;

import java.time.ZonedDateTime;

public interface ILogStreamEntry extends ILogStreamElement {
    ZonedDateTime getTimestamp();

    LogChannel getChannel();

    String getText();

    long getOwnerSectionSequenceNumber();
}
