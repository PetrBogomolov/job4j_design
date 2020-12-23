package ru.job4j.io.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    private static final long serialVersionUID = 234L;
    private final String name;
    private final String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Имя - " + name
                + " "
                + "Номер - " + number;
    }

    public static File serialization(Contact contact) throws IOException {
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream write = new FileOutputStream(tempFile);
        ObjectOutputStream oos = new ObjectOutputStream(write)) {
            oos.writeObject(contact);
        }
        return tempFile;
    }

    public static Contact deserialization(File file) throws IOException, ClassNotFoundException {
        Contact result;
        try (FileInputStream read = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(read)) {
            result = (Contact) ois.readObject();
        }
        return result;
    }

   public static void main(String[] args) throws IOException, ClassNotFoundException {
       Contact contact = new Contact("Name1", "Number1");
       Contact deserialized = deserialization(serialization(contact));
       System.out.println(contact);
       System.out.println(deserialized);
   }
}
