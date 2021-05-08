package com.hazse.executionlog.core;

import java.time.ZonedDateTime;
import java.util.List;

public interface ILogStream {
    String getId();

    ZonedDateTime getCreationTimeStamp();

    List<? extends ILogStreamElement> getElements();
}
