package ru.job4j.ood.lsp.parking.cars;

public class Car {
    private final String name;
    private final int size;

    public Car(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Car "
               + "name - " + name
               + ", size - " + size;
    }
}
