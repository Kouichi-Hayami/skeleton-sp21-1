package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private IntNode sentinel;
    private int size;

    private class IntNode {
        private IntNode prev;
        private T item;
        private IntNode next;

        private IntNode(IntNode p, T item, IntNode n) {
            prev = p;
            this.item = item;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        IntNode newNode = new IntNode(sentinel, item, sentinel.next);
        /*prev: Sentinel is always the first
        item: the stuff you want to add
        next: the existing first real node
        sentinel <-> A <-> B
        newNode: prev = sentinel, item = item, next = A
         */
        sentinel.next.prev = newNode;
        //snetinel.next is the current first item, assign the newNode to the first item
        sentinel.next = newNode; //sentinel.next is still pointing to A, assign it to newNode
        size++;
    }

    @Override
    public void addLast(T item) {
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        /*prev: the previous node of sentinel, since it is a circular structure,
        previous of sentinel is the last item of the list
        item：the stuff that you want to add
        sentinel: circular, will also be the last item of the list
         */
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode current = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        IntNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        IntNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        IntNode first = sentinel.next;
        for (int i = 0; i < index; i++) {
            first = first.next;
        }
        return first.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(IntNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque<?> other)) {
            return false;
        }
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
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private IntNode current;

        LinkedListIterator() {
            current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T returnItem = current.item;
            current = current.next;
            return returnItem;
        }
    }
}

