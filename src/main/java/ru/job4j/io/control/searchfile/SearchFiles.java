package ru.job4j.io.control.searchfile;

import ru.job4j.io.control.searchfile.FilterFiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;

public class SearchFiles {
    public List<Path> findFile(
            Path directory, String searchingFileName, String patternType
    ) throws IOException {
        FilterFiles exception = null;
        if (patternType.contains("-f")) {
            exception = new FilterFiles(path -> path.getFileName().toString().equals(searchingFileName));
        }
        if (patternType.contains("-m")) {
            exception = new FilterFiles(path -> path.getFileName().toString().endsWith(searchingFileName));
        }
        else if (patternType.contains("-r")) {
            exception = new FilterFiles(path -> path.getFileName().toString().equals(searchingFileName));
        }
        Files.walkFileTree(directory, Objects.requireNonNull(exception));
        return exception.getFiles();
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
