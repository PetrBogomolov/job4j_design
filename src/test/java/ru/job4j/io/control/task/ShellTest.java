package ru.job4j.io.control.task;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShellTest {

    @Test
    public void whenCdBack() {
        Shell shell = new Shell();
        shell.cd("/user/..");
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Test
    public void whenCdRoot() {
        Shell shell = new Shell();
        shell.cd("/");
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Test
    public void whenCdUserLocal() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("local");
        assertThat(
                shell.pwd(), is("/user/local")
        );
    }

    @Test
    public void whenCdUserBackFirstOption() {
        Shell shell = new Shell();
        shell.cd("user");
        shell.cd("..");
        assertThat(
                shell.pwd(), is("/")
        );
    }

    @Test
    public void whenCdUserBackSecondOption() {
        Shell shell = new Shell();
        shell.cd("home/user/something");
        shell.cd("..");
        assertThat(
                shell.pwd(), is("/home/user")
        );
    }
}
