package tester;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTestWithDebugLog() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDeque<Integer> correct = new ArrayDeque<>();

        StringBuilder log = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            int choice = StdRandom.uniform(0, 100);  // Now includes 0–4
            int val = StdRandom.uniform(1, 100);


            if (choice < 0.33){
                student.addFirst(val);
                correct.addFirst(val);
                log.append("addFirst(").append(val).append(")\n");
            }
            else if (choice < 0.67){
                student.addLast(val);
                correct.addLast(val);
                log.append("addLast(").append(val).append(")\n");
            }
            else {
                int size = student.size();
                log.append("size()\n");
                if(size > 0){
                    if (val > 50) {
                        Integer expected = correct.removeFirst();
                        Integer actual = student.removeFirst();
                        log.append("removeFirst()\n");
                        assertEquals(log.toString(), expected, actual);
                    }
                    else {
                        Integer expected = correct.removeLast();
                        Integer actual = student.removeLast();
                        log.append("removeLast()\n");
                        assertEquals(log.toString(), expected, actual);
                    }
                }
            }
        }
    }
}

