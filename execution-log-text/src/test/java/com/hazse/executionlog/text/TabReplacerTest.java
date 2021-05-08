package com.hazse.executionlog.text;

import com.hazse.executionlog.text.impl.TabReplacer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabReplacerTest {
    private static final TabReplacer replacer = new TabReplacer();

    @Test
    public void replaceNoTabs_works() {
        assertEquals("abrakadabra", replacer.transform("abrakadabra"));
    }

    @Test
    public void replaceOneTabs_works() {
        assertEquals("abraka    dabra", replacer.transform("abraka\tdabra"));
    }

    @Test
    public void replaceMultipleTabs_works() {
        assertEquals(
                "a    b    r    a    k    a    d    a    b    r    a",
                replacer.transform("a\tb\tr\ta\tk\ta\td\ta\tb\tr\ta")
        );
    }

    @Test
    public void replaceTabChain_works() {
        assertEquals("abraka        dabra", replacer.transform("abraka\t\tdabra"));
    }

    @Test
    public void nullInput_works() {
        assertNull(replacer.transform(null));
    }
}