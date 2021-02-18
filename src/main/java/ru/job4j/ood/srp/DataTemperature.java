package ru.job4j.ood.srp;

/*
 * Класс отвечает за определение времени и температуры
 * Нарушение принципа SRP здесь в том, что у класса несколько зон ответственности
 * Первая время, вторая температура. Правильнее было бы разделить имеющийся функционал
 * на два интерфейса.
 */

public interface DataTemperature<T> {

    /*
     * метод возвращает текущее время
     */
    T getTime();

    /*
     * метод возвращает текущую температуру
     */
    T getTemperature();
}
