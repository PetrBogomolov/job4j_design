package ru.job4j.garbage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class Cash {

    private final Map<String, SoftReference<String>> cash = new HashMap<>();

    public String getTextOfFile(String file) {
        String result;
        if (cash.containsKey(file)) {
            result = cash.get(file).get();
            if (result == null) {
                result = readFile(file);
            }
        } else {
            result = readFile(file);
        }
        return result;
    }

    private String readFile(String file) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            while (read.ready()) {
                read.lines().forEach(result::append);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cash.get(file) == null) {
            saveCash(file, result.toString());
        }
        return result.toString();
    }

    private void saveCash(String key, String value) {
        cash.put(key, new SoftReference<>(value));
    }

    public static void main(String[] args) {
        Cash cash = new Cash();
        System.out.println(cash.getTextOfFile("test.txt"));
    }
}
