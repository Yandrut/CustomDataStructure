package org.yandrut.CustomList;
import java.util.Iterator;

public class CustomArrayList<T> implements CustomList<T> {
    private T[] myArrayList;
    private int size;

    public CustomArrayList() {
        myArrayList = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public void add(T t) {
        if (size == myArrayList.length) {
            T[] temporaryArray = (T[]) new Object[size + size / 3];
            for (int i = 0; i < size(); i++) {
                temporaryArray[i] = myArrayList[i];
            }
            myArrayList = temporaryArray;
        }
        myArrayList[size] = t;
        size++;
    }

    @Override
    public T get(int index) {
        checkOutOfBounds(index);
            return myArrayList[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object toFind) {
        for (int i = 0; i < size; i++) {
            if (myArrayList[i].equals(toFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
       checkOutOfBounds(index);
        T removedObject = myArrayList[index];

        if (index == size - 1) {
            size--;
        } else {
            for (int i = index; i < size - 1; i++) {
                myArrayList[i] = myArrayList[i + 1];
            }
            size--;
        }
        return removedObject;
    }

    @Override
    public boolean remove(Object object) {
        if (this.contains(object)) {
            remove(indexOf(object));
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (myArrayList[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
        myArrayList[index] = element;
        return element;
    }

    @Override
    public boolean addAll(CustomList<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                this.add(list.get(i));
            }
            return true;
        }
        return false;
    }
    private void checkOutOfBounds(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            return myArrayList[index++];
        }
    }
}