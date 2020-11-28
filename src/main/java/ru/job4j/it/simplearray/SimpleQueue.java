package ru.job4j.it.simplearray;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inSize = 0;
    private int outSize = 0;

    public T poll() {
        T element = null;
        if (outSize == 0) {
            for (int i = 0; i < inSize; i++) {
                out.push(in.pop());
            }
            outSize = inSize;
            inSize = 0;
        }
        element = out.pop();
        outSize--;
        return element;
    }

    public void push(T value) {
        in.push(value);
        inSize++;
    }
}
