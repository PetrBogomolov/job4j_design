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
        List<String> result = SearchDublicate.searchDublicate(path);
        Iterator<String> it = result.listIterator();
        assertThat(it.next(), is("name file : file1.txt, size file : 0\r\n"));
        assertThat(it.next(), is("name file : file2.txt, size file : 0\r\n"));
    }
}