package ru.job4j.ood.lsp.parking.interfaces;

import ru.job4j.ood.lsp.parking.cars.Car;
import java.util.ArrayList;
import java.util.List;

public class Parking implements Park {
    private int placesForTruck;
    private int placesForPassenger;
    private final int limitTruckPlaces;
    private final List<Car> cars = new ArrayList<>();

    public Parking(int placesForTruck, int placesForPassenger) {
        this.placesForTruck = placesForTruck;
        this.placesForPassenger = placesForPassenger;
        this.limitTruckPlaces = placesForTruck;
    }

    @Override
    public boolean addCarInParking(Car car) {
       if (placesForPassenger > 0 && car.getSize() == 1) {
           cars.add(car);
           placesForPassenger--;
           return true;
       }
       if ((placesForTruck > 0 && car.getSize() > 1)) {
           cars.add(car);
           placesForTruck--;
           return true;
       }
       if (placesForTruck == 0 && car.getSize() > 1 && (placesForPassenger - car.getSize()) >= 0) {
           cars.add(car);
           placesForPassenger = placesForPassenger - car.getSize();
           return true;
       }
        return false;
    }

    @Override
    public int getFreePlacesForPassenger() {
        return placesForPassenger;
    }

    @Override
    public int getFreePlacesForTruck() {
        return placesForTruck;
    }

    @Override
    public boolean deleteCarFromParking(Car car) {
        if (cars.contains(car)) {
            if (car.getSize() == 1) {
                cars.remove(car);
                placesForPassenger++;
                return true;
            }
            if (car.getSize() > 1 && placesForTruck == limitTruckPlaces) {
                cars.remove(car);
                placesForPassenger = placesForPassenger + car.getSize();
                return true;
            }
            if (car.getSize() > 1) {
                cars.remove(car);
                placesForTruck++;
                return true;
            }
        }
        return false;
    }
}
