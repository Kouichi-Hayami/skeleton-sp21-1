package gh2;

import deque.Deque;
import edu.princeton.cs.algs4.StdRandom;

public class Drum extends GuitarString {


    private static final double DECAY = 1.0;

    public Drum(double frequency) {
        super(frequency / 1.5);
    }

    @Override
    public void tic() {
        double first = super.buffer.removeFirst();
        double second = super.buffer.get(0);
        double newSample = DECAY * 0.5 * (first + second);
        double probability = StdRandom.uniform();
        if (probability < 0.5) {
            super.buffer.addLast(newSample);
        } else {
            super.buffer.addLast(-newSample);
        }
    }
}