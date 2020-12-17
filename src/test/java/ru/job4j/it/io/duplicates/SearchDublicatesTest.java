package ru.job4j.it.io.duplicates;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchDublicatesTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File firstDublicate;

    @Before
    public void setup() throws IOException {
        folder.newFile("file1.txt");
        folder.newFile("file2.txt");
        folder.newFile("file3.txt");
        folder.newFolder("folder");
        firstDublicate = folder.newFile("folder//file1.txt");
        firstDublicate = folder.newFile("folder//file2.txt");
    }

    @Test
    public void whenDirectoryContainEqualsPath() throws IOException {
      List<String> expect = List.of("name file : file1.txt, size file : 0" + System.lineSeparator(),
               "name file : file2.txt, size file : 0" + System.lineSeparator());
      assertThat(SearchDublicate.searchDublicate(folder.getRoot().toPath()), is(expect));
    }
}