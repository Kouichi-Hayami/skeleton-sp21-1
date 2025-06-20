package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList buggyList = new BuggyAList();
        buggyList.addLast(4);
        buggyList.addLast(5);
        buggyList.addLast(6);

        AListNoResizing AList = new AListNoResizing();
        AList.addLast(4);
        AList.addLast(5);
        AList.addLast(6);

        assertEquals(buggyList.size(), AList.size());
        assertEquals(buggyList.removeLast(), AList.removeLast());
    }
}
