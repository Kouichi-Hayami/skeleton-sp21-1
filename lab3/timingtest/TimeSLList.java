package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        // Define the different list sizes you want to test
        int[] testSizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int ops = 10000;

        // Loop through each test size
        for (int N : testSizes) {
            SLList<Integer> testList = new SLList<>();
            for (int i = 0; i < N; i++) {
                testList.addLast(i);
            }
        Stopwatch sw = new Stopwatch();
        // Start timing how long it takes to call getLast() `ops` times
        for (int i = 0; i < ops; i++) {
            testList.getLast();
        }
        double timeInSeconds = sw.elapsedTime();
        Ns.addLast(N);//store current list size
        times.addLast(timeInSeconds);//store the current time
        opCounts.addLast(ops);//store how many operations

        }
        printTimingTable(Ns, times, opCounts);



    }

}
