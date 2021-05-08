package com.hazse.executionlog.text.impl;

import com.hazse.executionlog.text.ITextTransformer;

public class CarriageReturnRemover implements ITextTransformer {
    @Override
    public String transform(String input) {
        if (input != null){
            if (input.contains("\r")) {
                return input.substring(input.lastIndexOf("\r"));
            }
        }

        return input;
    }
}
