package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.interfaces.output.Output;
import ru.job4j.ood.isp.menu.model.Task;
import java.util.ArrayList;
import java.util.List;

public class Menu implements Output {

    private final List<Task> tasks = new ArrayList<>();

    public void addInMenu(Task task) {
        tasks.add(task);
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
        Task task1 = new Task("Task 1", List.of());
        Task task11 = new Task("Task 1.1", List.of());
        Task task111 = new Task("Task 1.1.1", List.of());
        Task task2 = new Task("Task 2", List.of());
        task1.addChild(task11);
        task11.addChild(task111);
        menu.addInMenu(task1);
        menu.addInMenu(task2);
        menu.print();
    }
}
