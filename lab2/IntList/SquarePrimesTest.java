package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquareWithoutPrimes() {
        IntList lst = IntList.of(14, 15, 16, 20, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 20 -> 18", lst.toString());
        assertFalse(changed);
    }
    @Test
    public void testAllPrimes() {
        IntList lst = IntList.of(5, 7, 11, 13, 17);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("25 -> 49 -> 121 -> 169 -> 289", lst.toString());
        assertTrue(changed);
    }
    @Test
    public void testLastPrimes() {
        IntList lst = IntList.of(6, 4, 6, 8, 5);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("6 -> 4 -> 6 -> 8 -> 25", lst.toString());
        assertTrue(changed);
    }
}
