package ru.job4j.it.simplearray;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
       return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}
