package com.hazse.executionlog.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hazse.executionlog.core.LogChannel;
import com.hazse.executionlog.inmemory.LogStorage;
import com.hazse.executionlog.inmemory.LogStream;
import com.hazse.executionlog.inmemory.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LogSerializerTest {
    @Test
    public void testLogSerializationWorks() throws JsonProcessingException {
        LogStorage logStorage = new LogStorage();
        Logger log = logStorage.createLogger("test-1");

        log.createSection("TestSection");

        log.createEntry("Entry-1");
        log.createEntry("Entry-2");
        log.createEntry("Entry-3", LogChannel.Error);
        log.createEntry("Entry-4");

        log.close();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new LogSerializationModule());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);

        LogStream logStream = logStorage.getLogStream("test-1");
        String serializedLog = objectMapper.writeValueAsString(logStream);

        assertNotNull(serializedLog);
    }
}