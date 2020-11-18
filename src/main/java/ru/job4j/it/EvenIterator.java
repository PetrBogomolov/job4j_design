package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;


    public EvenIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return findEvenNumberIndex() != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        point = findEvenNumberIndex();
        return data[point];
    }

    public int findEvenNumberIndex() {
        return IntStream.range(point, data.length)
                .filter(i -> data[i] % 2 == 0)
                .findFirst()
                .orElse(-1);
    }

    @Override
    public void remove() {
       throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        throw new UnsupportedOperationException();
    }
}
