package ru.job4j.io.control.task;

import java.util.Arrays;
import java.util.LinkedList;

public class Shell {
    private final LinkedList<String> currentDirectory = new LinkedList<>();
    private final static String REGEX = "(?=/)";

    public void cd(String path) {
        if (path.equals("/")) {
            currentDirectory.removeAll(currentDirectory);
            currentDirectory.add("/");
            return;
        }
        if (!path.startsWith("/")) {
            currentDirectory.add("/" + path);
        } else {
            String[] linesWay = path.split(REGEX);
            currentDirectory.addAll(Arrays.asList(linesWay));
        }
        if (currentDirectory.getLast().equals("/..")) {
            currentDirectory.pollLast();
            currentDirectory.pollLast();
            if (currentDirectory.size() == 0) {
                currentDirectory.add("/");
            }
        }
    }

    public String pwd() {
        StringBuilder result = new StringBuilder();
        currentDirectory.forEach(result::append);
        return result.toString();
    }
}
