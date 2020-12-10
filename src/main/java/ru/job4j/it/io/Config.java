package ru.job4j.it.io;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<>();


    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try(BufferedReader read = new BufferedReader(new FileReader(path))) {
            List<String> lines = read.lines().collect(Collectors.toList());
            for (String line : lines) {
                fillValues(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillValues(String element) {
        if (element.length() != 0) {
            int equals = element.indexOf("=");
            int slash = element.indexOf("//");
            if(slash != -1) {
                values.put(element.substring(0, equals), element.substring(equals +1, slash));
            } else {
                values.put(element.substring(0, equals), element.substring(equals +1));
            }
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Config("app.propertie"));
        String str = "     ";
        System.out.println(str.trim().length());

    }
}
