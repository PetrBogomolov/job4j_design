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
        return dublicate.result();
    }

   private static class SearchDublicates extends SimpleFileVisitor<Path> {
        private final List<String> paths = new ArrayList<>();
        private final HashSet<String> container = new HashSet<>();

        public List<String> result() {
            return paths;
        }

        private void filter(String path) {
             if(!container.add(path)) {
                 paths.add(path);
             }
        }

        private String formatter(Path path) {
            return String.format("name file : %s, size file : %s%n",
                    path.getFileName().toString(), path.toFile().length());
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            filter(formatter(path));
            return CONTINUE;
        }
   }
}
