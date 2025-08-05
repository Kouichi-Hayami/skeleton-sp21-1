package flik;

import org.junit.Test;

import static org.junit.Assert.*;

public class filk {
    @Test
    public void testSmallNumbers() {
        for (int i = 0; i < 128; i++) {
            assertTrue("Failed for i = " + i, Flik.isSameNumber(i, i));
        }
    }

    @Test
    public void testLargeNumbers() {
        for (int i = 128; i < 200; i++) {
            assertTrue("Failed for i = " + i, Flik.isSameNumber(i, i));
        }
    }

    @Test
    public void testDifferentNumbers() {
        assertFalse(Flik.isSameNumber(100, 101));
    }
}



