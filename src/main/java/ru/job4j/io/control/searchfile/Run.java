package ru.job4j.io.control.searchfile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Predicate;

public class Run {
    public static void main(String[] args) throws IOException {
        GetData key = new GetData(args);
        key.searchKey();
        Predicate<Path> predicate = PredicateFactory.createPredicate(
                key.getSearchingFileName(), key.getPatternType()
        );
        SearchFiles find = new SearchFiles();
        find.recordingResult(
                find.findFile(key.getDirectiry(), predicate),
                key.getTargetFileName()
        );
    }
}