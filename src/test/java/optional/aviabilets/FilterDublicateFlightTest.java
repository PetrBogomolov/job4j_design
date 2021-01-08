package optional.aviabilets;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FilterDublicateFlightTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private FilterDublicateFlights filter;
    private List<String> result;

    @Before
    public void setup() {
        filter = new FilterDublicateFlights();
        result = new ArrayList<>();
    }

    @Test
    public void whenDublicateRemove() throws IOException {
        Path sourse = new File("flights.txt").toPath();
        Path target = folder.newFile("noDublicate").toPath();
        filter.removeDuplicatesFlights(sourse, target);
        try (BufferedReader in = new BufferedReader(new FileReader(String.valueOf(target)))) {
            in.lines().forEach(result::add);
        }
        Iterator<String> it = result.listIterator();
        assertThat(it.next(), is("AFT7528"));
        assertThat(it.next(), is("AK56"));
        assertThat(it.next(), is("AK1214"));
        assertThat(it.next(), is("A4456"));
        assertThat(it.next(), is("A4457"));
    }
}