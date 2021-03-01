package ru.job4j.ood.lsp.parking.interfaces;

import ru.job4j.ood.lsp.parking.Type;
import ru.job4j.ood.lsp.parking.cars.Car;
import java.util.ArrayList;
import java.util.List;

public class Parking implements Park {

    private int initialCapacity;
    private List<Car> cars = new ArrayList<>();

    public Parking(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public boolean addCarInParking(Car car) {
        if (initialCapacity > 0 && car.getType().equals(Type.PASSENGER)) {
            cars.add(car);
            initialCapacity--;
            return true;
        } else if (initialCapacity > 2 && car.getType().equals(Type.TRUCK)) {
            cars.add(car);
            initialCapacity = initialCapacity - 3;
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
            if (car.getType().equals(Type.PASSENGER)) {
                cars.remove(car);
                initialCapacity++;
                return true;
            }
            if (car.getType().equals(Type.TRUCK)) {
                cars.remove(car);
                initialCapacity = initialCapacity + 3;
                return true;
            }
        } else {
            System.out.println("This car wasn't park in this parking");
        }
        return false;
    }
}
