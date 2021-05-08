package com.hazse.executionlog.jsonl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hazse.executionlog.core.ILogStream;
import com.hazse.executionlog.core.ILogStreamElement;
import com.hazse.executionlog.jsonl.jackson.JsonlSerializationModule;
import com.hazse.executionlog.jsonl.pojo.LogStreamHeader;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class JsonLinesSerializer {
    private final ObjectMapper objectMapper;

    public JsonLinesSerializer() {
        this.objectMapper = createConfiguredObjectMapper();
    }

    protected ObjectMapper createConfiguredObjectMapper() {
        ObjectMapper retValue = new ObjectMapper();

        retValue.registerModule(new JsonlSerializationModule());
        retValue.registerModule(new JavaTimeModule());
        retValue.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        retValue.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);

        return retValue;
    }

    public String serializeLogStream(ILogStream logStream) {
        return this.serializeLogStream(logStream, true, -1);
    }

    public String serializeLogStream(ILogStream logStream, boolean addHeader, long fromSequenceNumber) {
        StringWriter retValue = new StringWriter();

        serializeLogStreamTo(logStream, retValue);

        return retValue.toString();
    }

    public void serializeLogStreamTo(ILogStream logStream, Writer writer) {
        PrintWriter outputWriter = new PrintWriter(writer);

        doSerializeWithPrintWriter(outputWriter, logStream, true, -1);
    }

    public void serializeLogStreamTo(ILogStream logStream, Writer retValue, boolean autoFlush, boolean addHeader,
                                     long fromSequenceNumber) {
        PrintWriter outputWriter = new PrintWriter(retValue, autoFlush);

        doSerializeWithPrintWriter(outputWriter, logStream, addHeader, fromSequenceNumber);
    }

    protected void doSerializeWithPrintWriter(PrintWriter outputWriter, ILogStream logStream, boolean addHeader,
                                              long fromSequenceNumber) {
        serializeLogStreamWith(outputWriter::println, logStream, addHeader, fromSequenceNumber);
    }

    public List<String> serializeLogStreamAsList(ILogStream logStream, boolean addHeader, long fromSequenceNumber) {
        List<String> retValue = new ArrayList<>();

        serializeLogStreamWith(retValue::add, logStream, addHeader, fromSequenceNumber);

        return retValue;
    }

    public void serializeLogStreamWith(Consumer<String> consumer, ILogStream logStream, boolean addHeader,
                                       long fromSequenceNumber) {
        if (logStream != null) {
            try {
                if (addHeader) {
                    LogStreamHeader header = new LogStreamHeader(logStream.getId(), logStream.getCreationTimeStamp());
                    consumer.accept(objectMapper.writeValueAsString(header));
                }

                List<? extends ILogStreamElement> elements = logStream.getElements();
                if (elements != null) {
                    for (ILogStreamElement element : elements) {
                        if (element.getSequenceNumber() >= fromSequenceNumber) {
                            consumer.accept(objectMapper.writeValueAsString(element));
                        }
                    }
                }
            }
            catch (JsonProcessingException e) {
                throw new JsonSerializerException("Error serializing into JsonLines format.", e);
            }
        }
    }
}
