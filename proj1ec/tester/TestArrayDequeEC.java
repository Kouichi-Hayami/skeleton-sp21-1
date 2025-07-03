package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomizedTestWithDebugLog() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();

        StringBuilder log = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            double choice = StdRandom.uniform();
            int val = StdRandom.uniform(1, 100);

            if (choice < 0.25) {
                student.addFirst(val);
                correct.addFirst(val);
                log.append("addFirst(").append(val).append(")\n");

            } else if (choice < 0.5) {
                student.addLast(val);
                correct.addLast(val);
                log.append("addLast(").append(val).append(")\n");

            } else if (choice < 0.75) {
                int expectedSize = correct.size();
                int actualSize = student.size();
                log.append("size()\n");
                assertEquals(log.toString(), expectedSize, actualSize);

            } else {
                int size = student.size();
                log.append("size()\n");

                if (size > 0) {
                    Integer expected, actual;
                    if (val > 50) {
                        expected = correct.removeFirst();
                        actual = student.removeFirst();
                        log.append("removeFirst()\n");
                    } else {
                        expected = correct.removeLast();
                        actual = student.removeLast();
                        log.append("removeLast()\n");
                    }
                    assertEquals(log.toString(), expected, actual);
                }
            }
        }
    }
}
