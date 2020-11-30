package ru.job4j.it.simplearray;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> array = new SimpleArray<>(10);

    public void add(T model) {
        if (!contain(model)) {
            array.add(model);
        }
    }

    private boolean contain(T model) {
        boolean result = false;
        for (T element : array) {
            if (element.equals(model)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
