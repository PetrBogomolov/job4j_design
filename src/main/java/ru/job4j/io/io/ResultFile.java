package ru.job4j.io.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            out.write(convert(4).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String convert(int size) {
        int[][] input = Matrix.multiple(size);
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                string.append(input[i][j]).append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
