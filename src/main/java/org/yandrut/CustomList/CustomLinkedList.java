package org.yandrut.CustomList;
import java.util.Iterator;
import java.util.Objects;

public class CustomLinkedList<E> implements CustomList<E> {

    private static class Node<E> {
        private E currentElement;
        private Node<E> previous;
        private Node<E> next;

        public Node (E element) {
            this.currentElement = element;
        }

        public E getCurrentElement() {
            return currentElement;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;

    public CustomLinkedList() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    @Override
    public void add(E t) {
        Node<E> currentNode = new Node<>(t);
            if (this.isEmpty()) {
                first = last = currentNode;
            }
            else if (size >= 1) {
               last.next = currentNode;
               currentNode.previous = last;
               last = currentNode;
            }
        size++;
    }

    @Override
    public E get(int index) {
        throwIfIndexOutOfBounds(index);

        Node <E> currentNode = first;
        int flag = 0;
        while (Objects.nonNull(currentNode) && flag != index) {
            currentNode = currentNode.next;
            flag++;
        }
        return currentNode.getCurrentElement();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object obj) {
        Node <E> currentNode = first;
        while (currentNode != null) {
            if (obj.equals(currentNode.getCurrentElement())) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        throwIfIndexOutOfBounds(index);

        E forRemoval = null;
        int currentIndex = 1;

        // Removing the only element if size is 1
        if (this.size() == 1) {
            forRemoval = first.getCurrentElement();
            first = last = null;
        }
        // Removing first element if size is more than 1
        else if (index == 0) {
            forRemoval = first.getCurrentElement();
            first.next.previous = null;
            first = first.next;
        }
        //Removing last element
        else if (index == size() -1) {
            forRemoval = last.getCurrentElement();
            last.previous.next = null;
            last = last.previous;
        } else {
            Node <E> currentNode = first.next;
            Node <E> previousOfCurrent = currentNode.previous;
            Node <E> nextOfCurrent = currentNode.next;

            // Removing at all the other possible scenarios
            while (Objects.nonNull(currentNode)) {
                if (currentIndex == index) {
                    forRemoval = currentNode.getCurrentElement();
                    previousOfCurrent.next = currentNode.next;
                    nextOfCurrent.previous = previousOfCurrent;
                    currentNode = currentNode.next;
                    currentIndex++;
                    break;
                }
            }
        }
        size--;
        return forRemoval;
    }

    @Override
    public boolean remove(Object obj) {
        if (this.contains(obj)) {
            remove(indexOf(obj));
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        Node <E> currentNode = first;
        int currentIndex = 0;
        while (Objects.nonNull(currentNode)) {
            if (o.equals(currentNode.getCurrentElement())) {
                return currentIndex;
            }
            currentNode = currentNode.next;
            currentIndex++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (Objects.isNull(first) && Objects.isNull(last));
    }

    @Override
    public E set(int index, E element) {
        throwIfIndexOutOfBounds(index);
        Node <E> currentNode = first;
        int currentIndex = 0;
        while (currentNode != null) {
            if (currentIndex == index) {
                currentNode.currentElement = element;
                return currentNode.getCurrentElement();
            }
            currentNode = currentNode.next;
            currentIndex++;
        }
        return null;
    }

    @Override
    public boolean addAll(CustomList<E> myList) {
        if (myList != null) {
            for (int i = 0; i < myList.size(); i++) {
                this.add(myList.get(i));
            }
            return true;
        }
        return false;
    }

    private void throwIfIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private Node <E> currentNode;

        public MyIterator () {
            currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return (currentNode != null);
        }

        @Override
        public E next() {
            E currentElement = currentNode.getCurrentElement();
              currentNode = currentNode.next;
          return currentElement;
        }
    }
}