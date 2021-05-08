package com.hazse.executionlog.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hazse.executionlog.core.ILogStreamEntry;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class LogEntrySerializer extends StdSerializer<ILogStreamEntry> {
    public LogEntrySerializer() {
        super(ILogStreamEntry.class, false);
    }

    @Override
    public void serialize(ILogStreamEntry value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField(LogSerializer.ELEMENT_TYPE_FIELD, LogSerializer.ELEMENT_TYPE_ENTRY);
        gen.writeNumberField("seq", value.getSequenceNumber());
        gen.writeNumberField("sectionSeq", value.getOwnerSectionSequenceNumber());
        gen.writeObjectField("timeStamp", value.getTimestamp());
        gen.writeObjectField("channel", value.getChannel());
        gen.writeStringField("text", value.getText());

        gen.writeEndObject();
    }
}
