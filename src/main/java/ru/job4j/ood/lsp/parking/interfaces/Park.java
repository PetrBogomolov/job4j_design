package ru.job4j.ood.lsp.parking.interfaces;

import ru.job4j.ood.lsp.parking.cars.Car;

/**
 * интрефейс описывающий функционал парковки
 */
public interface Park {

    /**
     * Метод заполняет парковку машиной
     * @param car машина для парковки
     */
    boolean addCarInParking(Car car);

    /**
     * метод показывает сколько свободных мест осталось на парковке для пассажирских авто
     * @return количество свободных мест
     */
    int getFreePlacesForPassenger();

    /**
     * метод показывает сколько свободных мест осталось на парковке для грузовиков
     * @return количество свободных мест
     */
    int getFreePlacesForTruck();

    /**
     * метод освобождает место на парковке
     * @param car машина освободившая парковочное место
     */
    boolean deleteCarFromParking(Car car);
}
