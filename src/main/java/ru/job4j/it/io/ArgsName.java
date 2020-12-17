package ru.job4j.it.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Parameters not specified");
        }
        for (String parametr : args) {
            if (!parametr.contains("=")) {
                throw new UnsupportedOperationException(
                        "Данный формат строки не соответсвует шаблону key=value"
                );
            } else {
                String[] lines = parametr.split("=");
                values.put(lines[0].replaceFirst("-", ""), lines[1]);
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName name = new ArgsName();
        name.parse(args);
        return name;
    }
}
