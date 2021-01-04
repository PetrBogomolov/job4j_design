package ru.job4j.io.control;

import java.nio.file.Files;
import java.nio.file.Path;

public class GetData {
    private Path directory;
    private String targetFileName;
    private String searchingFileName;
    private String patternType;
    private final String[] args;

    public GetData(String[] args) {
        this.args = args;
    }

    public void searchKey() {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER."
            );
        }
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
            searchingFileName = searchingFileName.replace("*", "");
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

    public Path getDirectiry() {
        return directory;
    }

    public String getSearchingFileName() {
        return searchingFileName;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public String getPatternType() {
        return patternType;
    }
}
