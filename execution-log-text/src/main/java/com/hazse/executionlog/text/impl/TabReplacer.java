package com.hazse.executionlog.text.impl;

import com.hazse.executionlog.text.ITextTransformer;

public class TabReplacer implements ITextTransformer {
    public static final int DEFAULT_TAB_LENGTH = 4;

    private final String tabReplacement;

    public TabReplacer() {
        this(DEFAULT_TAB_LENGTH);
    }

    public TabReplacer(int tabLength) {
        if (tabLength <= 0) {
            throw new IllegalArgumentException("Tab-replacement length must be a positive number.");
        }

        tabReplacement = " ".repeat(tabLength);
    }

    @Override
    public String transform(String input) {
        if (input != null) {
            if (input.contains("\t")) {
                return input.replaceAll("\\t", tabReplacement);
            }
        }

        return input;
    }
}
