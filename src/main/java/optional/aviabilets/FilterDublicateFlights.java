package optional.aviabilets;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;

public class FilterDublicateFlights {
    private final static String CODE_COMPANY_AZ_AZ_AZ = "[A-Z][A-Z][A-Z][0-9]{1,5}";
    private final static String CODE_COMPANY_AZ_AZ_SPACE = "[A-Z][A-Z][ ][0-9]{1,5}";
    private final static String CODE_COMPANY_AZ_AZ = "[A-Z][A-Z][0-9]{1,5}";
    private final static String CODE_COMPANY_AZ09_AZ09_SPACE = "[A-Z 0-9][A-Z 0-9][ ][0-9]{1,5}";
    private final static String CODE_COMPANY_AZ09_AZ = "[A-Z 0-9][A-Z][0-9]{1,5}";
    private final static String CODE_COMPANY_AZ_AZ09 = "[A-Z][A-Z 0-9][0-9]{1,5}";
    private final static String GENERAL_REGEX = String.format(
            "%s|%s|%s|%s|%s|%s|",
            CODE_COMPANY_AZ_AZ_AZ, CODE_COMPANY_AZ_AZ_SPACE, CODE_COMPANY_AZ_AZ,
            CODE_COMPANY_AZ09_AZ09_SPACE, CODE_COMPANY_AZ09_AZ, CODE_COMPANY_AZ_AZ09
    );

    public void removeDuplicatesFlights(Path source, Path target) {
        Set<String> result = new LinkedHashSet<>();
        try (BufferedReader in = new BufferedReader(new FileReader(String.valueOf(source)))) {
            in.lines()
                    .map(String::trim)
                    .filter(this::filterLine)
                    .map(this::prepareFlightForCompare)
                    .forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordResult(result, target);
    }

    private boolean filterLine(String line) {
        return !validateLine(line) && line.matches(GENERAL_REGEX);
    }

    private boolean validateLine(String line) {
        return line.isEmpty() || line.length() > 7;
    }

    private String prepareFlightForCompare(String flight) {
        StringBuilder builder = new StringBuilder();
        if (flight.contains(" ")) {
            flight = flight.replace(" ", "");
        }
        if (flight.matches(CODE_COMPANY_AZ_AZ)) {
            flight = standardizeFlightNumber(flight, builder, "(?<=[A-Z][A-Z])");
        } else if (flight.matches(CODE_COMPANY_AZ_AZ_AZ)) {
            flight = standardizeFlightNumber(flight, builder, "(?<=[A-Z][A-Z][A-Z])");
        } else if (flight.matches(CODE_COMPANY_AZ09_AZ)) {
            flight = standardizeFlightNumber(flight, builder, "(?<=[A-Z 0-9][A-Z])");
        } else if (flight.matches(CODE_COMPANY_AZ_AZ09)) {
        flight = standardizeFlightNumber(flight, builder, "(?<=[A-Z][A-Z 0-9])");
    }
        return flight;
    }

    private String standardizeFlightNumber(String flight, StringBuilder builder, String delimiter) {
        String[] elementsFlight = flight.split(delimiter);
        while (elementsFlight[1].startsWith("0")) {
            elementsFlight[1] = elementsFlight[1].replaceFirst("0", "");
        }
        builder.append(elementsFlight[0]).append(elementsFlight[1]);
        return builder.toString();
    }

    private void recordResult(Set<String> flights, Path target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(String.valueOf(target))
                ))) {
            flights.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
