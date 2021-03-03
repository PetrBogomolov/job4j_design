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
    private Car scania;
    private Park parking;

    @Before
    public void setup() {
        reno = new Passenger("Logan");
        scania = new Truck("Scania", 3);
        parking = new Parking(4, 4);
    }

    @Test
    public void whenParkingEmptyThen5PlacesForPassenger() {
        assertThat(4, is(parking.getFreePlacesForPassenger()));
    }

    @Test
    public void whenParkingEmptyThen5PlacesForTruck() {
        assertThat(4, is(parking.getFreePlacesForTruck()));
    }

    @Test
    public void whenParkedOneTruckThenRemainThreePlacesForTruck() {
        parking.addCarInParking(scania);
        assertThat(3, is(parking.getFreePlacesForTruck()));
    }

    @Test
    public void whenParkedTwoPassengersThenRemainTwoPlaceForPassenger() {
        parking.addCarInParking(reno);
        parking.addCarInParking(reno);
        assertThat(2, is(parking.getFreePlacesForPassenger()));
    }

    @Test
    public void whenNoPlacesInParkingForPassenger() {
        parking.addCarInParking(reno);
        parking.addCarInParking(reno);
        parking.addCarInParking(reno);
        parking.addCarInParking(reno);
        assertThat(false, is(parking.addCarInParking(reno)));
    }

    @Test
    public void whenAllPlacesForTruckIsTakenThenTruckParkedInPassengerPlace() {
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        assertThat(0, is(parking.getFreePlacesForTruck()));
        assertThat(1, is(parking.getFreePlacesForPassenger()));
    }

    @Test
    public void whenAllPlacesIsTaken() {
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(reno);
        assertThat(false, is(parking.addCarInParking(reno)));
    }

    @Test
    public void whenDeleteCarWhichWasNotParkedInThisParkingThenFalse() {
        assertThat(false, is(parking.deleteCarFromParking(reno)));
    }

    @Test
    public void whenDeeCarWhichWasNotParkedInThisParkingThenFalse() {
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.addCarInParking(scania);
        parking.deleteCarFromParking(scania);
        parking.deleteCarFromParking(scania);
        parking.deleteCarFromParking(scania);
        parking.deleteCarFromParking(scania);
        parking.deleteCarFromParking(scania);
        assertThat(4, is(parking.getFreePlacesForTruck()));
        assertThat(4, is(parking.getFreePlacesForPassenger()));
    }
}
