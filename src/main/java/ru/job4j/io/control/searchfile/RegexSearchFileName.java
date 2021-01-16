package ru.job4j.io.control.searchfile;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RegexSearchFileName implements Predicate<Path> {
    private final Pattern pattern;

    public RegexSearchFileName(String searchFileName) {
        this.pattern = Pattern.compile(searchFileName);
    }

    @Override
    public boolean test(Path path) {
        return pattern.matcher(path.getFileName().toString()).matches();
    }

    public static String prepareSearchFileName(String searchFileName) {
        boolean run = true;
        while (run) {
            if (searchFileName.startsWith("*")) {
                searchFileName = searchFileName.replace("*", ".*");
                break;
            }
            if (searchFileName.contains(".")) {
                searchFileName = searchFileName.replace(".", "\\.");
                break;
            }
            run = false;
        }
        return searchFileName;
    }
}