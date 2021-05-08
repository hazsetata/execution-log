package com.hazse.executionlog.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.hazse.executionlog.core.ILogStream;

import java.io.IOException;

public class LogSerializer extends StdSerializer<ILogStream> {
    public static final String ELEMENT_TYPE_FIELD = "type";
    public static final String ELEMENT_TYPE_SECTION = "section";
    public static final String ELEMENT_TYPE_ENTRY = "entry";

    public LogSerializer() {
        super(ILogStream.class, false);
    }

    @Override
    public void serialize(ILogStream value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("id", value.getId());
        gen.writeObjectField("creationTimeStamp", value.getCreationTimeStamp());
        gen.writeObjectField("elements", value.getElements());

        gen.writeEndObject();
    }
}
