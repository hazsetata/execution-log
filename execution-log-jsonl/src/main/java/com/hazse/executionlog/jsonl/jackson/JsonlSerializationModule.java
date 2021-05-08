package com.hazse.executionlog.jsonl.jackson;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hazse.executionlog.core.ILogStream;
import com.hazse.executionlog.core.ILogStreamEntry;
import com.hazse.executionlog.core.ILogStreamSection;
import com.hazse.executionlog.jackson.LogEntrySerializer;
import com.hazse.executionlog.jackson.LogSectionSerializer;
import com.hazse.executionlog.jackson.LogSerializer;
import com.hazse.executionlog.jsonl.pojo.LogStreamHeader;

public class JsonlSerializationModule extends SimpleModule {
    public final static Version VERSION = new Version(
            0, 1, 0, null,
            "com.hazse.executionlog",
            "execution-log-jsonl"
    );

    public JsonlSerializationModule() {
        super(VERSION);
        addSerializer(ILogStreamEntry.class, new LogEntrySerializer());
        addSerializer(ILogStreamSection.class, new LogSectionSerializer());
        addSerializer(LogStreamHeader.class, new LogStreamHeaderSerializer());
    }
}
