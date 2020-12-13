package ru.job4j.it.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    private static final String REGEX = ";";
    private static final String STATUS400 = "400";
    private static final String STATUS500 = "500";

    public void unavailable(String sourse, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(sourse))) {
            List<String> result = new ArrayList<>();
            String cursor = null;
            String storage = null;
            while (read.ready()) {
                String currentLine = read.readLine();
                if (cursor == null && (currentLine.contains(STATUS400) || currentLine.contains(STATUS500))) {
                    String[] lines = currentLine.split(" ");
                    storage = lines[1] + REGEX;
                    cursor = currentLine;
                }
                if (cursor != null && (!currentLine.contains(STATUS400) && !currentLine.contains(STATUS500))) {
                    String[] lines = currentLine.split(" ");
                    result.add(storage + lines[1] + REGEX);
                    cursor = null;
                }
            }
            PrintWriter write = new PrintWriter(new FileOutputStream(target));
            for (String element : result) {
                write.println(element);
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
