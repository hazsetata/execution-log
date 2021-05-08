package com.hazse.executionlog.text.wrapper;

import com.hazse.executionlog.core.ILogStreamEntry;
import com.hazse.executionlog.core.ILogStreamSection;
import com.hazse.executionlog.core.ILogger;
import com.hazse.executionlog.core.LogChannel;
import com.hazse.executionlog.text.ITextTransformer;

public class LoggerWrapper<S extends ILogStreamSection, E extends ILogStreamEntry, L extends ILogger<S, E>> implements ILogger<S,E> {
    private final L actualLogger;
    private final ITextTransformer textTransformer;
    private final boolean transformTitle;

    public LoggerWrapper(L actualLogger, ITextTransformer textTransformer, boolean transformTitle) {
        this.actualLogger = actualLogger;
        this.textTransformer = textTransformer;
        this.transformTitle = transformTitle;
    }

    @Override
    public S createSection(String title, boolean setAsDefault) {
        return actualLogger.createSection(
                transformTitle ? textTransformer.transform(title) : title,
                setAsDefault
        );
    }

    @Override
    public S getDefaultSection() {
        return actualLogger.getDefaultSection();
    }

    @Override
    public E createEntry(String text, LogChannel channel, S owner) {
        return actualLogger.createEntry(
                textTransformer.transform(text),
                channel,
                owner
        );
    }

    @Override
    public void close() {
        actualLogger.close();
    }
}
