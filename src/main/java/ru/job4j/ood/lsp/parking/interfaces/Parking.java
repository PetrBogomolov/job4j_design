package ru.job4j.ood.lsp.parking.interfaces;

import ru.job4j.ood.lsp.parking.cars.Car;

public class Parking implements Park {

    @Override
    public void addCarInParking(Car car) {

    }

    @Override
    public int getFreePlaces() {
        return 0;
    }

    @Override
    public String defineTypeCar(Car car) {
        return null;
    }

    @Override
    public void deleteCarFromParking(int place) {

    }
}
