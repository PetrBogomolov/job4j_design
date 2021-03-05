package ru.job4j.ood.isp.menu.model;

import ru.job4j.ood.isp.menu.interfaces.action.Action;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String name;
    private final List<Action> actions;
    private final List<Task> children = new ArrayList<>();

    public Task(String name, List<Action> actions) {
        this.name = name;
        this.actions = actions;
    }

    public String getName() {
        return name;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Task> getChildren() {
        return children;
    }

    public void addChild(Task task) {
        children.add(task);
    }

    @Override
    public String toString() {
        return getName();
    }
}
