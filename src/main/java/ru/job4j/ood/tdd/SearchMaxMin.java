package ru.job4j.ood.tdd;

import java.util.Comparator;
import java.util.List;

public class SearchMaxMin {

    public <T> T max(List<T> values, Comparator<T> comparator) {
        return searchMax(values, comparator);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return searchMax(values, comparator.reversed());
    }

    private <T> T searchMax(List<T> values, Comparator<T> comparator) {
        T max = values.get(0);
        for (T value : values) {
            if (comparator.compare(max, value) < 0) {
                max = value;
            }
        }
        return max;
    }
}
