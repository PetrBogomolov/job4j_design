package ru.job4j.ood.ocp;

/*
 * расширить взаимодействие не изменяя класс и Animal и его методы невозможно.
 * необходимо вынести метод void speak() в интерфейс, и для каждого животного писать реализацию
 */
public class Example3 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.speakDog();
        animal.speakDCat();
    }

    static class Animal {
        public void speakDog() {
            System.out.println("гав");
        }

        public void speakDCat() {
            System.out.println("мяу");
        }
    }
}
