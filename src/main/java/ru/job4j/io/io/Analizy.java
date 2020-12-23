package ru.job4j.io.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private static final String REGEX = ";";
    private static final String STATUS400 = "400";
    private static final String STATUS500 = "500";

    public void unavailable(String sourse, String target) {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(sourse))) {
            String cursor = null;
            String storage = null;
            while (read.ready()) {
                String currentLine = read.readLine();
                if (cursor == null && (currentLine.contains(STATUS400)
                        || currentLine.contains(STATUS500))) {
                    String[] lines = currentLine.split(" ");
                    storage = lines[1] + REGEX;
                    cursor = currentLine;
                }
                if (cursor != null && (!currentLine.contains(STATUS400)
                        && !currentLine.contains(STATUS500))) {
                    String[] lines = currentLine.split(" ");
                    result.add(storage + lines[1] + REGEX);
                    cursor = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        write(result, target);
    }

    private void write(List<String> list, String file) {
        try (PrintWriter write = new PrintWriter(new FileOutputStream(file))) {
           list.forEach(write::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
