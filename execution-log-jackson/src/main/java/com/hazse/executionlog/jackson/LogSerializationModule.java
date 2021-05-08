package com.hazse.executionlog.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hazse.executionlog.core.ILogStream;
import com.hazse.executionlog.core.ILogStreamEntry;
import com.hazse.executionlog.core.ILogStreamSection;

public class LogSerializationModule extends SimpleModule {
    public final static Version VERSION = new Version(
            0, 1, 0, null,
            "com.hazse.executionlog",
            "execution-log-jackson"
    );

    public LogSerializationModule() {
        super(VERSION);
        addSerializer(ILogStreamEntry.class, new LogEntrySerializer());
        addSerializer(ILogStreamSection.class, new LogSectionSerializer());
        addSerializer(ILogStream.class, new LogSerializer());
    }
}
