package ru.job4j.io.control.searchfile;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles {
    public List<Path> findFile(Path directory, Predicate<Path> search) throws IOException {
        FilterFiles filter = new FilterFiles(search);
        Files.walkFileTree(directory, filter);
        return filter.getFiles();
    }

    public void recordingResult(List<Path> paths, String targetFileName) {
        try (PrintWriter write = new PrintWriter(targetFileName)) {
            for (Path element : paths) {
                write.println(element.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}