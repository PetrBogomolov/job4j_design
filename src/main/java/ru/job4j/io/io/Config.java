package ru.job4j.io.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines()
                    .map(String::trim)
                    .forEach(this::fillValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillValues(String element) {
        if (element.length() == 0 || element.startsWith("#") || !element.contains("=")) {
            return;
        } else {
            int equals = element.indexOf("=");
            values.put(element.substring(0, equals), element.substring(equals + 1));
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
}
