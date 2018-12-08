package com.wikia.tibia.usecases;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class FixItemsTest {

    private FixItems target;

    @Before
    public void setup() {
        target = new FixItems();
    }

    @Ignore
    @Test
    public void testCheckItems() {
        target.checkItems();
    }

}