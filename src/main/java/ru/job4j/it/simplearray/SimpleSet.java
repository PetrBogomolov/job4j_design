package ru.job4j.it.simplearray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> array = new SimpleArray<>(10);

    public void add(T model) {
        array.add(model);
    }

    public T get(int index) {
       return array.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int expModCount = array.getModCount();
            private Object[] save = array.getContainer();

            @Override
            public boolean hasNext() {
                return index < array.getPossition();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expModCount != array.getModCount()) {
                    throw new ConcurrentModificationException();
                }
                return (T) save[index++];
            }
        };
    }
}
