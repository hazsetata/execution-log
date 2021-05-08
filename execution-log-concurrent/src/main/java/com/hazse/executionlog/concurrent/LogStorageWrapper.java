package com.hazse.executionlog.concurrent;

import com.hazse.executionlog.core.*;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("unused")
public class LogStorageWrapper<
        S extends ILogStreamSection, E extends ILogStreamEntry,
        R extends ILogger<S, E>, L extends ILogStream, O extends ILogStorage<S, E, R, L>> implements ILogStorage<S, E, R, L> {
    private final O actualStorage;
    private final ReentrantReadWriteLock readWriteLock;

    public LogStorageWrapper(O actualStorage) {
        this.actualStorage = actualStorage;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public R createLogger(String id) {
        readWriteLock.writeLock().lock();
        try {
            return actualStorage.createLogger(id);
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public Set<String> getLogIds() {
        readWriteLock.readLock().lock();
        try {
            return actualStorage.getLogIds();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public L getLogStream(String id) {
        readWriteLock.readLock().lock();
        try {
            return actualStorage.getLogStream(id);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void deleteLog(String id) {
        readWriteLock.writeLock().lock();
        try {
            actualStorage.deleteLog(id);
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void deleteLogsCreatedBefore(ZonedDateTime timeStamp) {
        readWriteLock.writeLock().lock();
        try {
            actualStorage.deleteLogsCreatedBefore(timeStamp);
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
