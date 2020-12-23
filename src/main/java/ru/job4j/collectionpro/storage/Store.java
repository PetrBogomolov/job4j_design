package ru.job4j.collectionpro.storage;

import java.util.function.Consumer;

public interface Store<T extends Base> {

    void add(T model);

    boolean replace(int id, T model);

    boolean delete(int id);

    T fyndId(int id);

    void forEach(Consumer<T> action);
}
