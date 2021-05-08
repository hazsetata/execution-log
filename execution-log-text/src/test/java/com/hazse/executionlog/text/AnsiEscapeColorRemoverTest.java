package com.hazse.executionlog.text;

import com.hazse.executionlog.text.impl.AnsiEscapeColorRemover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnsiEscapeColorRemoverTest {
    private static final AnsiEscapeColorRemover remover = new AnsiEscapeColorRemover();

    @Test
    public void cleaningNoEscape_works() {
        assertEquals("abrakadabra", remover.transform("abrakadabra"));
    }

    @Test
    public void cleaningTest_1_works() {
        assertEquals("abrakadabra", remover.transform("\\u001B[5mabrakadabra\\u001B[0m"));
    }

    @Test
    public void cleaningTest_2_works() {
        assertEquals("abrakadabra", remover.transform("\\u001B[5;20mabrakadabra\\u001B[0m"));
    }

    @Test
    public void nullInput_works() {
        assertNull(remover.transform(null));
    }
}