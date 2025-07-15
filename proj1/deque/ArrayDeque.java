package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst; //next index first will insert
    private int nextLast; //next index last will insert
    private static final int DEFAULT_CAPACITY = 8;

    //这个list并不一定是从0开始的，所以用两个pointer把它固定住了

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        nextFirst = DEFAULT_CAPACITY - 1;
        nextLast = 0;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        int currentIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            newItems[i] = items[currentIndex];
            currentIndex = plusOne(currentIndex);
        }
        items = newItems;
        nextFirst = newSize - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst); // nextfirst往回走那就是减法
        size++;

    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int currentIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = plusOne(currentIndex);
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = plusOne(nextFirst); //the first item will be removed, so the nextfirst + 1
        T first = items[nextFirst];
        items[nextFirst] = null;
        size--;
        checkUsage();
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast); //the lst item will be removed
        T last = items[nextLast];
        items[nextLast] = null;
        size--;
        checkUsage();
        return last;
    }

    private void checkUsage() {
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int actualIndex = (plusOne(nextFirst) + index) % items.length;
        return items[actualIndex];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            Object a = this.get(i);
            Object b = other.get(i);
            if (a == null) {
                if (b != null) {
                    return false;
                }
            } else if (!a.equals(b)) {
                return false;
            }
        }
        return true;
    }



    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int current;

        ArrayDequeIterator() {
            current = 0; //already handle the circular in get(index) method
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            T returnItem = get(current);
            current++;
            return returnItem;
        }
    }

}
