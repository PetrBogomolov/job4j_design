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
        String[] linesWay = path.split(REGEX);
        if (!path.startsWith("/")) {
            currentDirectory.add("/" + linesWay[0]);
            currentDirectory.addAll(Arrays.asList(linesWay).subList(1, linesWay.length));
        } else {
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

    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.cd("/..");
        System.out.println(shell.pwd());
    }
}
