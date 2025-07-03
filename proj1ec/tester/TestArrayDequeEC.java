package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomTest(){
        StudentArrayDeque<Integer> student = new StudentArrayDeque();
        ArrayDeque<Integer> correct = new ArrayDeque();

        StringBuilder string = new StringBuilder();

        for (int i = 0; i < 10000; i++){
            int choice = StdRandom.uniform(0, 4); //来判断我选择哪个test
            Integer randomVal = StdRandom.uniform(1, 100);
            switch (choice){
                case 0:
                    student.addFirst(randomVal);
                    correct.addFirst(randomVal);
                    string.append("addFirst(").append(randomVal).append(")\n");
                    break;
                case 1:
                    student.addLast(randomVal);
                    correct.addLast(randomVal);
                    string.append("addLast(").append(randomVal).append(")\n");
                    break;
                case 2:
                    if (!student.isEmpty() && !correct.isEmpty()) {
                        Integer actual = student.removeFirst();
                        Integer expected = correct.removeFirst();
                        string.append("removeFirst()\n");
                        assertEquals(string.toString(),actual, expected);
                    }
                    break;
                case 3:
                    if (!student.isEmpty() && !correct.isEmpty()){
                        Integer actual = student.removeLast();
                        Integer expected = correct.removeLast();
                        string.append("removeLast()\n");
                        assertEquals(string.toString(),actual, expected);
                    }
                    break;
            }
        }




    }
}
