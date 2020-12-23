package ru.job4j.collectionpro.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(int id, T model) {
        int i = findIndexById(id);
        if (i != -1) {
            mem.set(i, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        int i = findIndexById(id);
        if (i != -1) {
            mem.remove(i);
            return true;
        }
        return false;
    }

    @Override
    public T fyndId(int id) {
        for (T element : mem) {
            if (element.getId() == id) {
                return element;
            }
        }
        return null;
    }

    @Override
    public void forEach(Consumer<T> action) {
        for (T element : mem) {
            action.accept(element);
        }
    }

    public int findIndexById(int id) {
        T element = fyndId(id);
        if (element == null) {
            return -1;
        }
        return mem.indexOf(element);
    }
}
