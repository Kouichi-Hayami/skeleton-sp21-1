package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void test() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < StdRandom.uniform(0, 1000000); i++) {
            double choice = StdRandom.uniform();
            Integer randVal = StdRandom.uniform(0, 100);
            if (choice < 0.33) {
                student.addLast(randVal);
                correct.addLast(randVal);
                message += "addLast(" + randVal + ")\n";
            } else if (choice < 0.67) {
                student.addFirst(randVal);
                correct.addFirst(randVal);
                message += "addFirst(" + randVal + ")\n";
            } else {
                int size = student.size();
                message += "size()\n";
                if(size > 0){
                    if(randVal <50){
                        message+="removeFirst()\n";
                        assertEquals(message,correct.removeFirst(),student.removeFirst());
                    } else {
                        message +="removeLast()\n";
                        assertEquals(message,correct.removeLast(),student.removeLast());
                    }
                }
            }
        }
    }
}