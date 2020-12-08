package ru.job4j.it.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder number = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                number.append((char) read);
            }
            String[] numbres = number.toString().split(System.lineSeparator());
            for (String element : numbres) {
                boolean even = Integer.parseInt(element) % 2 == 0;
                if (even) {
                    System.out.println(element + " - even");
                } else {
                    System.out.println(element + " - not even");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
