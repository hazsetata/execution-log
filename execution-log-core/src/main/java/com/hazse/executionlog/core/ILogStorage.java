package com.hazse.executionlog.core;

import java.time.ZonedDateTime;
import java.util.Set;

public interface ILogStorage<S extends ILogStreamSection, E extends ILogStreamEntry, R extends ILogger<S, E>, L extends ILogStream> {
    R createLogger(String id);

    Set<String> getLogIds();

    L getLogStream(String id);

    void deleteLog(String id);

    void deleteLogsCreatedBefore(ZonedDateTime timeStamp);
}
