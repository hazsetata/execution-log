package com.hazse.executionlog.inmemory;

import com.hazse.executionlog.core.ILogStorage;
import com.hazse.executionlog.core.exception.LoggerException;

import java.time.ZonedDateTime;
import java.util.*;

public class LogStorage implements ILogStorage<LogStreamSection, LogStreamEntry, Logger, LogStream> {
    private Map<String, LogStream> logs;

    public LogStorage() {
        this.logs = new HashMap<>();
    }

    @Override
    public Logger createLogger(String id) {
        if (!logs.containsKey(id)) {
            LogStream log = new LogStream(id, ZonedDateTime.now());
            logs.put(id, log);

            return new Logger(log);
        }
        else {
            throw new LoggerException("Log with specified ID already exists");
        }
    }

    @Override
    public Set<String> getLogIds() {
        return Collections.unmodifiableSet(logs.keySet());
    }

    @Override
    public LogStream getLogStream(String id) {
        return logs.get(id);
    }

    @Override
    public void deleteLog(String id) {
        logs.remove(id);
    }

    @Override
    public void deleteLogsCreatedBefore(ZonedDateTime timeStamp) {
        List<String> idsToBeDeleted = new ArrayList<>();

        Set<Map.Entry<String, LogStream>> entries = logs.entrySet();
        for (Map.Entry<String, LogStream> entry: entries) {
            if (entry.getValue().getCreationTimeStamp().isBefore(timeStamp)) {
                idsToBeDeleted.add(entry.getKey());
            }
        }

        if (!idsToBeDeleted.isEmpty()) {
            for (String id : idsToBeDeleted) {
                deleteLog(id);
            }
        }
    }
}
