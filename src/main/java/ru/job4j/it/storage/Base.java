package ru.job4j.it.storage;

public class Base {
    private final int id;
    private final String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "/" + name;
    }
}
