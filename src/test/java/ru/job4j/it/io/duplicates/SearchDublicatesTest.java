package ru.job4j.it.io.duplicates;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchDublicatesTest {

    @Test
    public void whenDirectoryContainEqualsPath() throws IOException {
        Path path = new File("C://Train").toPath();
        List<String> result = Search.searchDublicate(path);
        Iterator<String> it = result.listIterator();
        assertThat(it.next(), is("file1.txt"));
        assertThat(it.next(), is("file2.txt"));
    }
}