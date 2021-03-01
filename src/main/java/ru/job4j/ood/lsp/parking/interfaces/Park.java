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
    void addCarInParking(Car car);

    /**
     * метод показывает сколько свободных мест осталось на парковке
     * @return количество свободных мест
     */
    int getFreePlaces();

    /**
     * метод определяет тип машины
     * @param car машины, которая будет парвковаться
     * @return тип машины
     */
    String defineTypeCar(Car car);

    /**
     * метод освобождает место на парковке
     * @param place номер парковочного места
     */
    void deleteCarFromParking(int place);
}
