package ru.job4j.it.io;

import java.io.*;

public class Analizy {
    public void unavailable(String sourse, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(sourse));
            PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
            StringBuilder newLine = new StringBuilder();
            String cursor = null;
            while (read.ready()) {
                String currentLine = read.readLine();
                if (cursor == null && (currentLine.contains("400") || currentLine.contains("500"))) {
                    String[] lines = currentLine.split(" ");
                    newLine.append(lines[1]).append(";");
                    cursor = currentLine;
                }
                if (cursor != null && (!currentLine.contains("400") && !currentLine.contains("500"))) {
                    String[] lines = currentLine.split(" ");
                    newLine.append(lines[1]).append(";").append(System.lineSeparator());
                    cursor = null;
                }
            }
            write.println(newLine.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
