package ru.job4j.it.io.archive;

public class ArgZip {
    private static final String DIRECTORY = "c:";
    private static final String FILE = "*";
    private static final String TARGET = ".zip";
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public void valid() {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER."
            );
        }
        if (args.length < 3) {
            throw new IllegalArgumentException("Not all data entered");
        }
    }

    private String chooseElement(String str) {
        valid();
        for (String element : args) {
            if (element.contains(str)) {
                return element;
            }
        }
        throw new NullPointerException();
    }

    public String directory() {
       return chooseElement(DIRECTORY);
    }

    public String exclude() {
        return chooseElement(FILE).replace(FILE, "");
    }

    public String output() {
       return chooseElement(TARGET);
    }
}
