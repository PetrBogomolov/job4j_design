package optional.aviabilets;

import java.io.File;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        FilterDublicateFlights filter = new FilterDublicateFlights();
        Path source = new File("flights.txt").toPath();
        Path target = new File("result.txt").toPath();
        filter.removeDuplicatesFlights(source, target);
    }
}
