package optional.aviabilets;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterDublicateFlight {
    private final static String AZAZAZ = "[A-Z][A-Z][A-Z][0-9]{1,5}";
    private final static String AZAZspace = "[A-Z][A-Z][ ][0-9]{1,5}";
    private final static String AZAZ = "[A-Z][A-Z][0-9]{1,5}";
    private final static String AZ09AZ09space = "[A-Z 0-9][A-Z 0-9][ ][0-9]{1,5}";
    private final static String AZ09AZ = "[A-Z 0-9][A-Z][0-9]{1,5}";
    private final static String AZAZ09 = "[A-Z][A-Z 0-9][0-9]{1,5}";
    private final static String AZ = "(.*)[A-Z](.*)";

    public void removeDublicateFlights(Path sourse, Path target) {
        Set<String> result = new LinkedHashSet<>();
        try (BufferedReader in = new BufferedReader(new FileReader(String.valueOf(sourse)))) {
            in.lines()
                    .map(String::trim)
                    .filter(this::valid)
                    .map(this::formatter)
                    .forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordingResult(result, target);
    }

    private boolean valid(String line) {
        Pattern pattern1 = Pattern.compile(AZAZAZ);
        Matcher matcher1 = pattern1.matcher(line);
        Pattern pattern2 = Pattern.compile(AZAZspace);
        Matcher matcher2 = pattern2.matcher(line);
        Pattern pattern3 = Pattern.compile(AZAZ);
        Matcher matcher3 = pattern3.matcher(line);
        Pattern pattern4 = Pattern.compile(AZ09AZ09space);
        Matcher matcher4 = pattern4.matcher(line);
        Pattern pattern5 = Pattern.compile(AZ09AZ);
        Matcher matcher5 = pattern5.matcher(line);
        Pattern pattern6 = Pattern.compile(AZAZ09);
        Matcher matcher6 = pattern6.matcher(line);
        Pattern pattern7 = Pattern.compile(AZ);
        Matcher matcher7 = pattern7.matcher(line);
        boolean run = true;
        boolean result = true;
        while (run) {
            if (line.isEmpty() || line.length() > 7) {
                result = false;
                break;
            }
            if (!matcher1.matches() && !matcher2.matches() && !matcher3.matches()
                                    && !matcher4.matches() && !matcher5.matches()
                                    && !matcher6.matches()) {
                result = false;
                break;
            }
            if (!matcher7.matches()) {
                result = false;
                break;
            }
            run = false;
        }
        return result;
    }

    private String formatter(String line) {
        StringBuilder builder = new StringBuilder();
        if (line.contains(" ")) {
            line = line.replace(" ", "");
        }
        if (line.matches(AZAZ)) {
            line = newLine(line, builder, "(?<=[A-Z][A-Z])");
        } else if (line.matches(AZAZAZ)) {
            line = newLine(line, builder, "(?<=[A-Z][A-Z][A-Z])");
        } else if (line.matches(AZ09AZ)) {
            line = newLine(line, builder, "(?<=[A-Z 0-9][A-Z])");
        } else if (line.matches(AZAZ09)) {
        line = newLine(line, builder, "(?<=[A-Z][A-Z 0-9])");
    }
        return line;
    }

    private String newLine(String line, StringBuilder builder, String delimiter) {
        String[] lines = line.split(delimiter);
        while (lines[1].startsWith("0")) {
            lines[1] = lines[1].replace("0", "");
        }
        builder.append(lines[0]).append(lines[1]);
        return builder.toString();
    }

    private void recordingResult(Set<String> flights, Path target) {
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
