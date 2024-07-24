package deque;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private static final int DEFAULTFIRST = 4;
    private static final int DEFAULTLAST = 5;
    private static final int DEFAULTARRAYSIZE = 8;
    private static final int RESIZEFACTOR = 2;
    private static final float USAGEFACTOR = 0.25F;
    public int size;
    public T[] items;
    public int nextFirst;
    public int nextLast;

    private class ArrayDeque61BIterator<T> implements Iterator<T>{
        int wizPos;

        public ArrayDeque61BIterator(){
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size();
        }

        @Override
        public T next() {
            T returnItem = (T) get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator<T>();
    }

    public ArrayDeque61B() {
        items = (T[]) new Object[DEFAULTARRAYSIZE];
        size = 0;
        nextFirst = DEFAULTFIRST;
        nextLast = DEFAULTLAST;
    }

    @Override
    public void addFirst(T x) {
        if (size < items.length) {
            items[nextFirst] = x;
            size += 1;
            nextFirst = Math.floorMod(nextFirst - 1, items.length);
        } else {
            resizeUp();
            addFirst(x);
        }
    }

    @Override
    public void addLast(T x) {
        if (size < items.length) {
            items[nextLast] = x;
            size += 1;
            nextLast = Math.floorMod(nextLast + 1, items.length);
        } else {
            resizeUp();
            addLast(x);
        }
    }

    private void resizeUp() {
        // creat a new array
        T[] resizedArray = (T[]) new Object[RESIZEFACTOR * items.length];
        for (int i = 0; i < items.length; i++) {
            resizedArray[i] = get(i);
        }
        items = resizedArray;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public List<T> toList() {
        List<T> lst = new LinkedList<>();
        for (int i = 0; i < size(); i++) {
            lst.add(get(i));
        }
        return lst;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeDown() {
        T[] resizedArray = (T[]) new Object[size() * RESIZEFACTOR];
        for (int i = 0; i < size(); i++) {
            resizedArray[i] = get(i);
        }
        items = resizedArray;
        nextFirst = items.length - 1;
        nextLast = size();
    }

    @Override
    public T removeFirst() {
        float usage = (float) size() / items.length;
        if (items.length >= 16 && usage < USAGEFACTOR) {
            resizeDown();
        }
        size -= 1;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        return removedItem;
    }

    @Override
    public T removeLast() {
        float usage = (float) size() / items.length;
        if (items.length >= 16 && usage < USAGEFACTOR) {
            resizeDown();
        }
        size -= 1;
        nextLast = Math.floorMod(nextLast - 1, items.length);
        T removedItem = items[nextLast];
        items[nextLast] = null;
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size()) {
            return null;
        } else {
            int i = Math.floorMod(nextFirst + 1 + index, items.length);
            return items[i];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ArrayDeque61B<?> other) {
            if (this.size() != other.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) != other.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}