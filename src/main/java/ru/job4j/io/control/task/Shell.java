package ru.job4j.io.control.task;

import java.util.ArrayList;
import java.util.List;

public class Shell {
    private final List<String> currentDirectory = new ArrayList<>();

    public void cd(String path) {
        currentDirectory.add("/");
        if (!path.startsWith("/")) {
            currentDirectory.add(path);
        } else {
            currentDirectory.add(path.replaceAll("/", ""));
        }
        if (path.contains("..")) {
            currentDirectory.removeAll(currentDirectory);
            currentDirectory.add("/");
        }
    }

    public String pwd() {
        StringBuilder result = new StringBuilder();
        currentDirectory.forEach(result::append);
        return result.toString();
    }

    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.cd("/user/");
        System.out.println(shell.pwd());
    }
}
