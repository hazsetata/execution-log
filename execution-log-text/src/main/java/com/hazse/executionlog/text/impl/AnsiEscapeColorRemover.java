package com.hazse.executionlog.text.impl;

import com.hazse.executionlog.text.ITextTransformer;

public class AnsiEscapeColorRemover implements ITextTransformer {
    public final String ANSI_ESCAPE_PATTERN = "\\\\u001B\\[[0-9;]*[ -/]*[@-~]";

    @Override
    public String transform(String input) {
        if (input != null) {
            return input.replaceAll(ANSI_ESCAPE_PATTERN, "");
        }

        return null;
    }
}
