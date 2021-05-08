package com.hazse.executionlog.text;

public class TextTransformerChain implements ITextTransformer {
    private final ITextTransformer[] transformers;

    public TextTransformerChain(ITextTransformer... transformers) {
        this.transformers = transformers;
    }

    @Override
    public String transform(String input) {
        if ((transformers != null) && (transformers.length > 0)) {
            String currentInput = input;

            for (ITextTransformer transformer : transformers) {
                currentInput = transformer.transform(currentInput);
            }

            return currentInput;
        }

        return input;
    }
}
