package ru.job4j.collectionpro;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] array;
    private int possition = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        if (possition >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[possition++] = model;
    }

    public void set(int index, T model) {
        int indexOf = Objects.checkIndex(index, possition);
        array[indexOf] = model;
    }

    public void remove(int index) {
        int indexOf = Objects.checkIndex(index, possition);
        array[indexOf] = null;
        possition--;
        System.arraycopy(array, index + 1, array, index, possition - index);
    }

    public T get(int index) {
        int indexOf = Objects.checkIndex(index, possition);
        return (T) array[indexOf];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < possition;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[index++];
            }
        };
    }
}
