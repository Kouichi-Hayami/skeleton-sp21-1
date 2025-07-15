package deque;

public interface Deque<T> extends Iterable<T> {
    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
        // can simplify to return size() == 0;
    }

    int size();

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);
}
