package com.hazse.executionlog.jsonl.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hazse.executionlog.jackson.LogSerializer;
import com.hazse.executionlog.jsonl.pojo.LogStreamHeader;

import java.io.IOException;

public class LogStreamHeaderSerializer extends StdSerializer<LogStreamHeader> {
    public static final String ELEMENT_TYPE_JSONL_HEADER = "jsonl-header";

    public LogStreamHeaderSerializer() {
        super(LogStreamHeader.class, false);
    }

    @Override
    public void serialize(LogStreamHeader value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField(LogSerializer.ELEMENT_TYPE_FIELD, ELEMENT_TYPE_JSONL_HEADER);
        gen.writeStringField("id", value.getId());
        gen.writeObjectField("creationTimeStamp", value.getCreationTimeStamp());

        gen.writeEndObject();
    }
}
