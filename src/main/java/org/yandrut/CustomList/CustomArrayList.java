package org.yandrut.CustomList;
import java.util.Iterator;
import java.util.Objects;

public class CustomArrayList<T> implements CustomList<T> {
    private T[] arrayList;
    private int size;

    public CustomArrayList() {
        arrayList = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public void add(T t) {
        if (size == arrayList.length) {
            T[] temporaryArray = (T[]) new Object[size + size / 3];
            for (int i = 0; i < size(); i++) {
                temporaryArray[i] = arrayList[i];
            }
            arrayList = temporaryArray;
        }
        arrayList[size] = t;
        size++;
    }

    @Override
    public T get(int index) {
        throwIfIndexOutOfBounds(index);
            return arrayList[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object toFind) {
        for (int i = 0; i < size; i++) {
            if (arrayList[i].equals(toFind)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
       throwIfIndexOutOfBounds(index);
        T removedObject = arrayList[index];

        if (index == size - 1) {
            size--;
        } else {
            for (int i = index; i < size - 1; i++) {
                arrayList[i] = arrayList[i + 1];
            }
            size--;
        }
        return removedObject;
    }

    @Override
    public boolean remove(Object object) {
        if (this.contains(object)) {
            this.remove(indexOf(object));
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (this.arrayList[i].equals(object)) {
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
        arrayList[index] = element;
        return element;
    }

    @Override
    public boolean addAll(CustomList<T> list) {
        if (Objects.nonNull(list)) {
            for (int i = 0; i < list.size(); i++) {
                this.add(list.get(i));
            }
            return true;
        }
        return false;
    }
    private void throwIfIndexOutOfBounds(int index) {
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
            return arrayList[index++];
        }
    }
}