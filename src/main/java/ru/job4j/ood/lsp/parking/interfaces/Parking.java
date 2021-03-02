package ru.job4j.ood.lsp.parking.interfaces;

import ru.job4j.ood.lsp.parking.cars.Car;
import java.util.ArrayList;
import java.util.List;

public class Parking implements Park {

    private int initialCapacity;
    private List<Car> cars = new ArrayList<>();

    public Parking(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    @Override
    public boolean addCarInParking(Car car) {
        if ((initialCapacity - car.getSize()) > 0) {
            cars.add(car);
            initialCapacity = initialCapacity - car.getSize();
            return true;
        } else {
            System.out.println("no places");
        }
        return false;
    }

    @Override
    public int getFreePlaces() {
        return initialCapacity;
    }

    @Override
    public boolean deleteCarFromParking(Car car) {
        if (cars.contains(car)) {
            cars.remove(car);
            initialCapacity = initialCapacity + car.getSize();
            return true;
        } else {
            System.out.println("This car wasn't park in this parking");
        }
        return false;
    }
}
