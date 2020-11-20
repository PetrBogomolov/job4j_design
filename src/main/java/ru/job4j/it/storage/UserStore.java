package ru.job4j.it.storage;

import java.util.function.Consumer;

public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(int id, User model) {
       return store.replace(id, model);
    }

    @Override
    public boolean delete(int id) {
        return store.delete(id);
    }

    @Override
    public User fyndId(int id) {
        return store.fyndId(id);

    }

    @Override
    public void forEach(Consumer<User> action) {
        store.forEach(System.out::println);
    }
}
