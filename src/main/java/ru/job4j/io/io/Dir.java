package ru.job4j.io.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("C://Users//Acer//IdeaProjects");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile())
            );
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile())
            );
        }
        System.out.printf(
                "name directory : %s, size directory : %s%n",
                file.getName(), file.getTotalSpace()
        );
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf(
                    "name file : %s, size file : %d%n",
                    subfile.getName(), subfile.length()
            );
        }
    }
}
