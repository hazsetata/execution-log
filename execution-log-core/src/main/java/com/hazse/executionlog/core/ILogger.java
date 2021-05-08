package com.hazse.executionlog.core;

public interface ILogger<S extends ILogStreamSection, E extends ILogStreamEntry> {
    default S createSection(String title) {
        return createSection(title, true);
    }

    S createSection(String title, boolean setAsDefault);

    S getDefaultSection();

    default E createEntry(String text) {
        return createEntry(text, LogChannel.Out);
    }

    default E createEntry(String text, LogChannel channel) {
        return createEntry(text, channel, getDefaultSection());
    }

    E createEntry(String text, LogChannel channel, S owner);

    void close();
}
