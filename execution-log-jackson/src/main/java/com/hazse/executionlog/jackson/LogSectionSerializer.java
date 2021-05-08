package com.hazse.executionlog.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hazse.executionlog.core.ILogStreamSection;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class LogSectionSerializer extends StdSerializer<ILogStreamSection> {
    public LogSectionSerializer() {
        super(ILogStreamSection.class, false);
    }

    @Override
    public void serialize(ILogStreamSection value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField(LogSerializer.ELEMENT_TYPE_FIELD, LogSerializer.ELEMENT_TYPE_SECTION);
        gen.writeNumberField("seq", value.getSequenceNumber());
        gen.writeStringField("title", value.getTitle());

        gen.writeEndObject();
    }
}
