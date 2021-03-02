package ru.job4j.ood.lsp.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.cars.Passenger;
import ru.job4j.ood.lsp.parking.cars.Truck;
import ru.job4j.ood.lsp.parking.interfaces.Park;
import ru.job4j.ood.lsp.parking.interfaces.Parking;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingMonitoringTest {
    private Car reno;
    private Car honda;
    private Car bmw;
    private Car kamaz;
    private Car scania;
    private Car ford;
    private Park parking;

    @Before
    public void setup() {
        reno = new Passenger("Logan", 1);
        honda = new Passenger("Civic", 1);
        bmw = new Passenger("m5", 1);
        kamaz = new Truck("Kamaz", 3);
        ford = new Truck("Ford", 3);
        scania = new Truck("Scania", 3);
        parking = new Parking(10);
    }

    @Test
    public void whenParkingEmptyThen10Places() {
        assertThat(10, is(parking.getFreePlaces()));
    }

    @Test
    public void whenAddInParking2TrucksThenRemain4FreePlaces() {
        parking.addCarInParking(ford);
        parking.addCarInParking(kamaz);
        assertThat(4, is(parking.getFreePlaces()));
    }

    @Test
    public void whenAddInParking4PassengerCarsThenRemain6FreePlaces() {
        parking.addCarInParking(reno);
        parking.addCarInParking(honda);
        parking.addCarInParking(honda);
        parking.addCarInParking(bmw);
        assertThat(6, is(parking.getFreePlaces()));
    }

    @Test
    public void whenNoPlacesInParking() {
        parking.addCarInParking(ford);
        parking.addCarInParking(kamaz);
        parking.addCarInParking(scania);
        assertThat(false, is(parking.addCarInParking(scania)));
    }

    @Test
    public void whenWasThreeTrucksDeleteOneTrucksThenRemain4FreePlaces() {
        parking.addCarInParking(ford);
        parking.addCarInParking(kamaz);
        parking.addCarInParking(scania);
        parking.deleteCarFromParking(scania);
        assertThat(4, is(parking.getFreePlaces()));
    }

    @Test
    public void whenDeleteCarWhichWasNotParkedInThisParking() {
        assertThat(false, is(parking.deleteCarFromParking(scania)));
    }
}
