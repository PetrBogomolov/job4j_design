package optional.aviabilets;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class FilterDublicateFlight {
    public void removeDublicateFlights(Path sourse, Path target) {
        Set<String> result = new HashSet<>();
        try (BufferedReader in = new BufferedReader(new FileReader(String.valueOf(sourse)))) {
            in.lines().map(this::formatter).forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordingResult(result, target);
    }

    private String formatter(String line) {
        if (line.contains(" ") || line.contains("0")) {
            line = line.replace(" ", "").replaceAll("0", "");
        }
        return line;
    }

    private void recordingResult(Set<String> flights, Path target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(String.valueOf(target))
                ))) {
            flights.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
