package com.hazse.executionlog.jsonl.pojo;

import java.time.ZonedDateTime;

public class LogStreamHeader {
    private String id;
    private ZonedDateTime creationTimeStamp;

    public LogStreamHeader(String id, ZonedDateTime creationTimeStamp) {
        this.id = id;
        this.creationTimeStamp = creationTimeStamp;
    }

    public String getId() {
        return id;
    }

    public ZonedDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }
}
