package optional.aviabilets;

import java.io.File;
import java.nio.file.Path;

public class Run {
    public static void main(String[] args) {
        FilterDublicateFlight filter = new FilterDublicateFlight();
        Path sourse = new File("").toPath();
        Path target = new File("").toPath();
        filter.removeDublicateFlights(sourse, target);
    }
}
