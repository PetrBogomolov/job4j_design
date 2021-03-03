package ru.job4j.ood.lsp.parking.cars;

public class Passenger extends Car {
    private final static int SIZE = 1;

    public Passenger(String name) {
        super(name, SIZE);
    }
}
