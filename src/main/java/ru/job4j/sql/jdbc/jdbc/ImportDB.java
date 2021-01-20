package ru.job4j.sql.jdbc.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportDB {
    private final static String REGEX = ";";
    private final String file;

    public ImportDB(String file) {
        this.file = file;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            while (read.ready()) {
                String[] elementOfUser = read.readLine().split(REGEX);
                users.add(new User(elementOfUser[0], elementOfUser[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void saveInDB(List<User> users) throws SQLException, ClassNotFoundException {
        ClassLoader loader = Settings.class.getClassLoader();
        Settings properties = new Settings();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            properties.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName(properties.getValue("driver"));
        Connection connection = DriverManager.getConnection(
                properties.getValue("url_for_spammer"),
                properties.getValue("login"),
                properties.getValue("password")
        );
        for (User user : users) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, email) VALUES (?, ?)"
            )) {
                statement.setString(1, user.name);
                statement.setString(2, user.email);
                statement.execute();
            }
        }

    }

    private static class User {
        private final String name;
        private final String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ImportDB db = new ImportDB("dump.txt");
        db.saveInDB(db.load());
    }
}
