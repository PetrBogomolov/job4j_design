package ru.job4j.collectionpro.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> current = findBy(parent);
        if (current.isPresent() && findBy(child).isEmpty()) {
            result = current.get().getChildren().add(new Node<>(child));
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return find(element -> element.getValue().equals(value));
    }

    public boolean isBinary() {
        return find(element -> element.getChildren().size() > 2).isEmpty();
    }

    private Optional<Node<E>> find(Predicate<Node<E>> predicate) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<E> element = queue.poll();
            if (predicate.test(element)) {
                result = Optional.of(element);
                break;
            }
            queue.addAll(element.getChildren());
        }
        return result;
    }
}
