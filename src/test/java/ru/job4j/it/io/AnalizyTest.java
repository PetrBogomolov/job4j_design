package ru.job4j.it.io;

import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    public List<String> read(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            lines = read.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Test
    public void unavailable() {
        Analizy analizy = new Analizy();
        String pathSourse = "server.log";
        String pathTager = "unavailable.csv";
        analizy.unavailable(pathSourse, pathTager);
        List<String> result = read(pathTager);
        Iterator<String> it = result.listIterator();
        assertThat(it.next(), is("10:58:01;10:59:01;"));
        assertThat(it.next(), is("11:01:02;11:02:02;"));
        assertThat(it.next(), is("11:02:03;11:03:15;"));
    }
}