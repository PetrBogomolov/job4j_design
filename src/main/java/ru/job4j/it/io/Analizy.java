package ru.job4j.it.io;

import java.io.*;

public class Analizy {
    private static final String REGEX = ";";
    private static final String STATUS400 = "400";
    private static final String STATUS500 = "500";

    public void unavailable(String sourse, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(sourse));
            PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
            String cursor = null;
            while (read.ready()) {
                String currentLine = read.readLine();
                if (cursor == null && (currentLine.contains(STATUS400) || currentLine.contains(STATUS500))) {
                    String[] lines = currentLine.split(" ");
                    write.print(lines[1] + REGEX);
                    cursor = currentLine;
                }
                if (cursor != null && (!currentLine.contains(STATUS400) && !currentLine.contains(STATUS500))) {
                    String[] lines = currentLine.split(" ");
                    write.println(lines[1] + REGEX);
                    cursor = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
