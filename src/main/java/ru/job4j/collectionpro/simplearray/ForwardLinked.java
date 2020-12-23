package ru.job4j.collectionpro.simplearray;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null, head);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        T element = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> next = head.next;
        element = head.value;
        head = null;
        head = next;
        return element;
    }

    public T deleteLast() {
        T element = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (head.next == null) {
            element = head.value;
            head = null;
        } else {
            Node<T> last = head;
            while (last.next.next != null) {
                last = last.next;
            }
            element = last.next.value;
            last.next = null;
        }
        return element;
    }

    public void revert() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> element = null;
        for (T value : this) {
            element = new Node<>(value, element, null);
        }
        head = element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
           private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
