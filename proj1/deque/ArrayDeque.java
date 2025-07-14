package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;//next index first will insert
    private int nextLast;//next index last will insert
    private static final int DEFAULT_CAPACITY = 8;

    //这个list并不一定是从0开始的，所以用两个pointer把它固定住了

    public ArrayDeque(){
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        nextFirst = DEFAULT_CAPACITY - 1;
        nextLast = 0;
    }
    public int plusOne(int index){
        return (index + 1) % items.length;
    }
    public int minusOne(int index){
        return (index - 1 + items.length) % items.length;
    }
    public void resize(int newSize){
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
    public void addFirst(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst); // nextfirst往回走那就是减法
        size++;

    }
    public void addLast(T item){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public int size(){
        return size;
    }
    public void printDeque(){
        int currentIndex = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[currentIndex] + " ");
            currentIndex = plusOne(currentIndex);
        }
        System.out.println();
    }
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        nextFirst = plusOne(nextFirst);//the first item will be removed, so the nextfirst + 1
        T first = items[nextFirst];
        items[nextFirst] = null;
        size--;
        checkUsage();
        return first;
    }
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        nextLast = minusOne(nextLast);//the lst item will be removed
        T last = items[nextLast];
        items[nextLast] = null;
        size--;
        checkUsage();
        return last;
    }

    public void checkUsage(){
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }
    public T get(int index){
        if (index >= size || index < 0){
            return null;
        }
        int actualIndex = (plusOne(nextFirst) + index) % items.length;
        return items[actualIndex];
    }
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof ArrayDeque)){
            return false;
            }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (size != other.size){
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))){
                return false;
            }
        }
        return true;
    }
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int current;

        public ArrayDequeIterator() {
            current = 0; //already handle the circular in get(index) method
        }
        public boolean hasNext(){
            return current < size;
        }
        public T next(){
            T returnItem = get(current);
            current ++;
            return returnItem;
        }
    }

}
