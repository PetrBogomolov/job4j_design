package optional.aviabilets;

import java.io.File;
import java.nio.file.Path;

public class Run {
    public static void main(String[] args) {
        FilterDublicateFlight filter = new FilterDublicateFlight();
        Path source = new File("flights.txt").toPath();
        Path target = new File("result.txt").toPath();
        filter.removeDublicateFlights(source, target);
    }
}
