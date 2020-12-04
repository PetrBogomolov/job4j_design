package ru.job4j.it.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (findBy(parent).isPresent() && findBy(child).isEmpty()) {
            result = findBy(parent).get().children.add(new Node<>(child));
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.value.equals(value)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

    public boolean isBinary() {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> element = queue.poll();
            if (element.children.size() <= 2) {
                return true;
            }
        }
        return false;
    }
}