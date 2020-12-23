package ru.job4j.io;

import org.junit.Test;
import ru.job4j.io.io.Config;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "app.propertie";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect")
        );
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }
}