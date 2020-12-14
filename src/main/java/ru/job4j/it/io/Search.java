package ru.job4j.it.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Search {
   public static List<Path> search(Path root, String ext) throws IOException {
       SearchFiles searchFiles = new SearchFiles(path -> path.toFile().getName().endsWith(ext));
       Files.walkFileTree(root, searchFiles);
       return searchFiles.getPaths();
   }
}
