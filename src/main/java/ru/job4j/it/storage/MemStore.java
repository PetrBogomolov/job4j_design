package ru.job4j.it.storage;

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
        T element = fyndId(id);
        if (element != null) {
            int i = mem.indexOf(element);
            mem.set(i, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        T element = fyndId(id);
        if (element != null) {
            mem.remove(element);
            return true;
        }
        return false;
    }

    @Override
    public T fyndId(int id) {
        for (T element : mem) {
            if(element.getId() == id) {
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
}
