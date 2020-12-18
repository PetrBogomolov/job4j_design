package ru.job4j.it.io.archive;

public class ArgZip {
    private String directory;
    private String exclude;
    private String output;
    private String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public void valid() {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Root folder is null. Usage java -jar dir.jar ROOT_FOLDER."
            );
        }
        if (args.length < 6) {
            throw new IllegalArgumentException("Not all data entered");
        }
    }

    private void chooseElement() {
        valid();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                directory = args[i +1];
            }
            if (args[i].equals("-e")) {
                exclude = args[i +1];
            }
            if (args[i].equals("-o")) {
                output = args[i +1];
            }
        }
        if (directory == null && exclude == null && output == null) {
            throw new IllegalArgumentException("The entered data is incorrect");
        }
    }

    public String directory() {
        chooseElement();
        return directory;
    }

    public String exclude() {
        chooseElement();
        return exclude;
    }

    public String output() {
        chooseElement();
        return output;
    }
}
