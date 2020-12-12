package ru.job4j.it.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        Analizy analizy = new Analizy();
        String sourse = "server.log";
        File target = folder.newFile("unavailable.csv");
        analizy.unavailable(sourse, target.getAbsolutePath());
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsoluteFile()))) {
            in.lines().forEach(result::add);
        }
        Iterator<String> it = result.listIterator();
        assertThat(it.next(), is("10:58:01;10:59:01;"));
        assertThat(it.next(), is("11:01:02;11:02:02;"));
        assertThat(it.next(), is("11:02:03;11:03:15;"));
    }
}