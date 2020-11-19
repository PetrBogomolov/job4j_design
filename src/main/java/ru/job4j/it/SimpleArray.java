package ru.job4j.it;

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
        for (int i = 0; i < array.length; i++) {
            if (i == indexOf) {
                array[i] = model;
            }
        }
    }

    public void remove(int index) {
        int indexof = Objects.checkIndex(index, possition);
        possition--;
        System.arraycopy(array, index + 1, array, index, possition - index);
    }


    public T get(int index) {
        int indexOf = Objects.checkIndex(index, possition);
        for (int i = 0; i < array.length; i++) {
            if (i == indexOf) {
                return (T) array[i];
            }
        }
        return (T) new Object();
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
