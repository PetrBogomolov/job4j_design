package ru.job4j.it.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.it.io.search.Search;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchTest {
    private static final String ext = "js";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        folder.newFile("file.js");
        folder.newFile("file.txt");
        folder.newFile("file.jar");
        folder.newFile("newFile.js");
    }

    @Test
    public void whenSerachByJS() throws IOException {
      List<Path> result = Search.search(folder.getRoot().toPath(), ext);
      Iterator<Path> it = result.iterator();
      assertThat(it.next().getFileName().toString(), is("file.js"));
      assertThat(it.next().getFileName().toString(), is("newFile.js"));
    }
}