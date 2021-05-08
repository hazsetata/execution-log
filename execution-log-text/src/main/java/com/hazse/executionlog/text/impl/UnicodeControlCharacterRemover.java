package com.hazse.executionlog.text.impl;

import com.hazse.executionlog.text.ITextTransformer;

public class UnicodeControlCharacterRemover implements ITextTransformer {
    @Override
    public String transform(String input) {
        if (input != null) {
            return input.replaceAll("\\p{C}", "");
        }

        return null;
    }
}
