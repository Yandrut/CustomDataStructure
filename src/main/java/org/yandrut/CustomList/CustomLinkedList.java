package org.yandrut.CustomList;
import java.util.Iterator;
import java.util.Objects;

public class CustomLinkedList<E> implements CustomList<E> {

    private static class Node<E> {
        private E currentElement;
        private Node<E> previousNode;
        private Node<E> nextNode;

        public Node (E element) {
            this.currentElement = element;
        }

        public E getCurrentElement() {
            return currentElement;
        }
    }

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;

    public CustomLinkedList() {
        this.firstNode = null;
        this.lastNode = null;
        size = 0;
    }

    @Override
    public void add(E t) {
        Node<E> currentNode = new Node<>(t);
            if (this.isEmpty()) {
                firstNode = lastNode = currentNode;
            }
            else if (size >= 1) {
               lastNode.nextNode = currentNode;
               currentNode.previousNode = lastNode;
               lastNode = currentNode;
            }
        size++;
    }

    @Override
    public E get(int index) {
        throwIfIndexOutOfBounds(index);

        Node <E> currentNode = firstNode;
        int currentIndex = 0;
        while (Objects.nonNull(currentNode) && currentIndex != index) {
            currentNode = currentNode.nextNode;
            currentIndex++;
        }
        return currentNode.getCurrentElement();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(Object obj) {
        Node <E> currentNode = firstNode;
        while (currentNode != null) {
            if (obj.equals(currentNode.getCurrentElement())) {
                return true;
            }
            currentNode = currentNode.nextNode;
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
            forRemoval = firstNode.getCurrentElement();
            firstNode = lastNode = null;
        }
        // Removing first element if size is more than 1
        else if (index == 0) {
            forRemoval = firstNode.getCurrentElement();
            firstNode.nextNode.previousNode = null;
            firstNode = firstNode.nextNode;
        }
        //Removing last element
        else if (index == size() -1) {
            forRemoval = lastNode.getCurrentElement();
            lastNode.previousNode.nextNode = null;
            lastNode = lastNode.previousNode;
        } else {
            Node <E> currentNode = firstNode.nextNode;
            Node <E> previousOfCurrent = currentNode.previousNode;
            Node <E> nextOfCurrent = currentNode.nextNode;

            // Removing at all the other possible scenarios
            while (Objects.nonNull(currentNode)) {
                if (currentIndex == index) {
                    forRemoval = currentNode.getCurrentElement();
                    previousOfCurrent.nextNode = currentNode.nextNode;
                    nextOfCurrent.previousNode = previousOfCurrent;
                    currentNode = currentNode.nextNode;
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
    public int indexOf(Object object) {
        Node <E> currentNode = firstNode;
        int currentIndex = 0;
        while (Objects.nonNull(currentNode)) {
            if (object.equals(currentNode.getCurrentElement())) {
                return currentIndex;
            }
            currentNode = currentNode.nextNode;
            currentIndex++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (Objects.isNull(firstNode) && Objects.isNull(lastNode));
    }

    @Override
    public E set(int index, E element) {
        throwIfIndexOutOfBounds(index);
        Node <E> currentNode = firstNode;
        int currentIndex = 0;

        while (currentNode != null) {
            if (currentIndex == index) {
                currentNode.currentElement = element;
                return currentNode.getCurrentElement();
            }
            currentNode = currentNode.nextNode;
            currentIndex++;
        }
        return null;
    }

    @Override
    public boolean addAll(CustomList<E> list) {
        if (Objects.nonNull(list)) {
            for (int i = 0; i < list.size(); i++) {
                this.add(list.get(i));
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
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<E> {
        private Node <E> currentNode;

        public CustomIterator() {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return (Objects.nonNull(currentNode));
        }

        @Override
        public E next() {
            E currentElement = currentNode.getCurrentElement();
              currentNode = currentNode.nextNode;
          return currentElement;
        }
    }
}