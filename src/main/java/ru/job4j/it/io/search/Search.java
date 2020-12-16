package ru.job4j.it.io.search;

import java.io.File;
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

    public static void main(String[] args) throws IOException {
       if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER.");
       }
       if (args.length < 2) {
            throw new IllegalArgumentException("The file extension is not specified");
       }
       Path path = new File(args[0]).toPath();
       String ext = args[1];
       search(path,ext).forEach(System.out::println);
    }
}
