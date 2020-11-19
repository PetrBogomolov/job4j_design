package ru.job4j.it;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Generics {
    static class Animal{
        private final String name;

        public Animal(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Predator extends Animal {
        public Predator(String name) {
            super(name);
        }
    }

    static class Tiger extends Predator {

        public Tiger(String name) {
            super(name);
        }
    }

    public static void main(String[] args) {
        Generics gen = new Generics();
        List<Animal> first = new ArrayList<>();
        List<Predator> second = new ArrayList<>();
        List<Tiger> third = new ArrayList<>();
        first.add(new Animal("Animal"));
        second.add(new Predator("Predator"));
        third.add(new Tiger("Tiger"));

        gen.printObject(first);
        gen.printObject(second);
        gen.printObject(third);
        System.out.println();

        gen.printBoundedWildCard(second);
        gen.printBoundedWildCard(third);
        System.out.println();

        gen.printLowerBoundedWildCard(first);
        gen.printLowerBoundedWildCard(second);

    }

    public void printObject(List<?> list) {
        for (Iterator<?> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printBoundedWildCard(List<? extends Predator> list) {
        for (Iterator<? extends Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }

    public void printLowerBoundedWildCard(List<? super Predator> list) {
        for (Iterator<? super Predator> it = list.iterator(); it.hasNext(); ) {
            Object next = it.next();
            System.out.println("Текущий элемент: " + next);
        }
    }
}
