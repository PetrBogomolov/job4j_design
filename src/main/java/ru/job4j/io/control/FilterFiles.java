package ru.job4j.io.control;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FilterFiles extends SimpleFileVisitor<Path> {
    private final List<Path> files = new ArrayList<>();
    private final Predicate<Path> predicate;

    public FilterFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    public List<Path> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        if (predicate.test(path)) {
            files.add(path.toAbsolutePath());
        }
        return CONTINUE;
    }
}
