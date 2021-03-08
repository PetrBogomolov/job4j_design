package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.interfaces.output.Output;
import ru.job4j.ood.isp.menu.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu implements Output {

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void addSubTask(String taskName, Task subtask) {
        for (Task task : tasks) {
            if (!task.getName().equals(taskName)) {
                searchTask(taskName, task).ifPresent(value -> value.addChild(subtask));
            } else {
                task.addChild(subtask);
            }
        }
    }

    private Optional<Task> searchTask(String taskName, Task task) {
        Optional<Task> result = Optional.empty();
        if (!task.getChildren().isEmpty()) {
            for (Task child : task.getChildren()) {
                if (child.getName().equals(taskName)) {
                    result = Optional.of(child);
                    break;
                }
                searchTask(taskName, child);
            }
        }
        return result;
    }

    @Override
    public void print() {
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            builder.append(task).append(System.lineSeparator());
            searchChildren(task, builder);
        }
        System.out.println(builder.toString());
    }

    private void searchChildren(Task task, StringBuilder builder) {
        if (!task.getChildren().isEmpty()) {
            for (Task child : task.getChildren()) {
                builder.append("  ----").append(child).append(System.lineSeparator());
                searchChildren(child, builder);
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addTask(new Task("Task 1", List.of()));
        menu.addSubTask("Task 1", new Task("Task 1.1", List.of()));
        menu.addSubTask("Task 1.1", new Task("Task 1.1.1", List.of()));
        menu.addTask(new Task("Task 2", List.of()));
        menu.addSubTask("Task 2", new Task("Task 2.1", List.of()));
        menu.print();
    }
}
