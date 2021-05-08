package com.hazse.executionlog.jsonl;

import com.hazse.executionlog.core.LogChannel;
import com.hazse.executionlog.inmemory.LogStorage;
import com.hazse.executionlog.inmemory.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonLinesSerializerTest {
    @Test
    public void jsonlSerializerWorks() {
        LogStorage logStorage = new LogStorage();
        Logger log = logStorage.createLogger("test-1");

        log.createSection("TestSection");

        log.createEntry("Entry-1");
        log.createEntry("Entry-2");
        log.createEntry("Entry-3", LogChannel.Error);
        log.createEntry("Entry-4");

        log.close();

        JsonLinesSerializer serializer = new JsonLinesSerializer();
        String serializedLog = serializer.serializeLogStream(logStorage.getLogStream("test-1"));

        assertNotNull(serializedLog);
    }
}