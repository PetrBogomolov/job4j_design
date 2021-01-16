package ru.job4j.io.control.searchfile;

import java.nio.file.Path;
import java.util.function.Predicate;

public class PredicateFactory {
    public static Predicate<Path> createPredicate(String searchFileName, String patternType) {
        Predicate<Path> result = null;
            if (patternType.equals("-f")) {
                result = new RegexSearchFileName(searchFileName);
            }
            if (patternType.equals("-m")) {
                result = new RegexSearchFileName(
                        RegexSearchFileName.prepareSearchFileName(searchFileName)
                );
            }
            if (patternType.equals("-r")) {
                result = new RegexSearchFileName(searchFileName);
            }
        return result;
    }
}