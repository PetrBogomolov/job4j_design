package ru.job4j.it.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Search {
    public static List<String> searchDublicate(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exist %s", path.getFileName()));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory %s", path.getFileName()));
        }
        SearchDublicates dublicate = new SearchDublicates();
        Files.walkFileTree(path, dublicate);
        List<String> result = new ArrayList<>();
        HashSet<String> container = new HashSet<>();
        for (String element : dublicate.result()) {
            if (!container.add(element)) {
                result.add(element);
            }
        }
        return result;
    }

   private static class SearchDublicates extends SimpleFileVisitor<Path> {
        private final List<String> paths = new ArrayList<>();

        public List<String> result() {
            return paths;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            paths.add(path.getFileName().toString());
            return CONTINUE;
        }
   }
}
