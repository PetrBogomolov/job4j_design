package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.Type;

public class Car {
    private final String name;
    private final Type type;

    public Car(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Car : "
               + "name = " + name
               + ", type = " + type;
    }
}
