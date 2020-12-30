package ru.job4j.io.control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFile {
    private Path directory;
    private String targetFileName;
    private String searchingFileName;

    private final String[] args;

    public SearchFile(String[] args) {
        this.args = args;
    }

    public List<Path> findFile() throws IOException {
        FindFiles exception = new FindFiles(searchingFileName);
        Files.walkFileTree(directory, exception);
        return exception.getFiles();
    }

    public void searchKey() {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER."
            );
        }
        String patternType = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = Path.of(args[i + 1]);
            }
            if (args[i].equals("-o")) {
                targetFileName = args[i + 1];
            }
            if (args[i].equals("-n")) {
                searchingFileName = args[i + 1];
                patternType = args[i + 2];
                convertSearchingFileName(patternType);
            }
        }
        validKey();
    }

    private void convertSearchingFileName(String searcheType) {
        if (!searcheType.equals("-r") && !searcheType.equals("-f") && !searcheType.equals("-m")) {
            throw new IllegalArgumentException("The type fail is not correct");
        }
        if (searcheType.equals("-r")) {
            searchingFileName =  "^" + searchingFileName.replace(".", "\\.") + "$";
        }
        if (searcheType.equals("-m")) {
            searchingFileName = searchingFileName.replace("*", ".*");
        }
    }

    private void validKey() {
        if (!Files.exists(directory)) {
            throw new IllegalArgumentException("The directory is not specified");
        }
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", directory.getFileName())
            );
        }
        if (searchingFileName == null) {
            throw new IllegalArgumentException("The sourse fail is not specified");
        }
        if (targetFileName == null) {
            throw new IllegalArgumentException("The target fail is not specified");
        }
    }

    public void recordingResult() {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(targetFileName))) {
            for (Path element : findFile()) {
                write.write(element.toString() + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class FindFiles extends SimpleFileVisitor<Path> {
        private final List<Path> files = new ArrayList<>();
        private final String pattern;

        public FindFiles(String pattern) {
            this.pattern = pattern;
        }

        public List<Path> getFiles() {
            return files;
        }

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
            if (path.getFileName().toString().matches(pattern)) {
                files.add(path.toAbsolutePath());
            }
            return CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        SearchFile find = new SearchFile(args);
        find.searchKey();
        find.recordingResult();
        System.out.println(find.searchingFileName);
    }
}
