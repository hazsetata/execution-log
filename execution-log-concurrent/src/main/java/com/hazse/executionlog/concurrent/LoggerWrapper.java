package com.hazse.executionlog.concurrent;

import com.hazse.executionlog.core.ILogStreamEntry;
import com.hazse.executionlog.core.ILogStreamSection;
import com.hazse.executionlog.core.ILogger;
import com.hazse.executionlog.core.LogChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("unused")
public class LoggerWrapper<S extends ILogStreamSection, E extends ILogStreamEntry, R extends ILogger<S, E>> implements ILogger<S, E>{
    private final R actualLogger;
    private final ReentrantReadWriteLock readWriteLock;
    private final Map<S, ReentrantLock> sectionLocks;

    public LoggerWrapper(R actualLogger) {
        this.actualLogger = actualLogger;
        this.readWriteLock = new ReentrantReadWriteLock();
        this.sectionLocks = new HashMap<>();
    }

    @Override
    public S createSection(String title, boolean setAsDefault) {
        readWriteLock.writeLock().lock();
        try {
            S retValue = actualLogger.createSection(title, setAsDefault);
            sectionLocks.put(retValue, new ReentrantLock());

            return retValue;
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public S getDefaultSection() {
        readWriteLock.readLock().lock();
        try {
            return actualLogger.getDefaultSection();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public E createEntry(String text, LogChannel channel, S owner) {
        readWriteLock.readLock().lock();
        ReentrantLock sectionLock = null;
        try {
            sectionLock = sectionLocks.get(owner);
            sectionLock.lock();
            return actualLogger.createEntry(text, channel, owner);
        }
        finally {
            if (sectionLock != null) {
                sectionLock.unlock();
            }

            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void close() {
        readWriteLock.writeLock().lock();
        try {
            actualLogger.close();
        }
        finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
