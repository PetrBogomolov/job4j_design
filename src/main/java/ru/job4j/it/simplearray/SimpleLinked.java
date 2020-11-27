package ru.job4j.it.simplearray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinked<E> implements Iterable {
   private static class Node<E> {
       E item;
       Node<E> next;
       Node<E> prev;

       public Node(E item, Node<E> next, Node<E> prev) {
           this.item = item;
           this.next = next;
           this.prev = prev;
       }
   }

    private int modCount = 0;
    private transient int size = 0;
    private transient Node<E> first;
    private transient Node<E> last;

    public void addInEnd(E item) {
        final Node<E> l = last;
        Node<E> node = new Node<>(item, null, l);
        last = node;
        if (l == null) {
            first = node;
        } else {
            l.next = node;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            private int expModCount = modCount;

            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E item = first.item;
                first = first.next;
                return item;
            }
        };
    }
}
