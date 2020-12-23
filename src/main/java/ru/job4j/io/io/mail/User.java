package ru.job4j.io.io.mail;

import java.util.Objects;

/**
 * Класс описывающий пользователя
 */
public class User {
    private String name;
    private String country;

    public User(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
               && Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
