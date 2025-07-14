package gh2;

import deque.Deque;

public class Harp extends GuitarString {
    private Deque<Double> buffer;
    private static final double DECAY = 0.996;


    public Harp(double frequency) {
        super(frequency * 2);
    }

    @Override
    public void tic() {
        double first = super.buffer.removeFirst();
        double second = super.buffer.get(0);
        double newSample = DECAY * 0.5 * (first + second);
        super.buffer.addLast(-newSample);
    }
}