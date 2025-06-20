package randomizedtest;
import edu.princeton.cs.algs4.StdRandom;
import static org.junit.Assert.*;


public class randomizedTest {
    public static void main(String[] args) {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = buggy.size();
                System.out.println("size: " + size + ", size2: " + size2);
                assertEquals(size, size2);

            }
            else if (operationNumber == 2  && L.size() > 0){
                int Last = L.getLast();
                int Last2 = buggy.getLast();
                System.out.println("getLast(): " + Last + ", Last2: " + Last2);
                assertEquals(Last, Last2);
            }
            else if (operationNumber == 3 && L.size() > 0) {
                int removed = L.removeLast();
                int removed2 = buggy.removeLast();
                System.out.println("removeLast(): " + removed + ", removed2: " + removed2);
                assertEquals(removed, removed2);
            }
        }
    }

}
