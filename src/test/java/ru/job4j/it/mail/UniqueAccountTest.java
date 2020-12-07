package ru.job4j.it.mail;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UniqueAccountTest {

    private UniqueAccount account;

    @Before
    public void setup() {
        account = new UniqueAccount();
    }

    @Test
    public void whenEmailEqualsThenMerge() {
        account.addUser(new User("Petr Bogomolov", "Rus"));
        account.addEmail("Petr Bogomolov", "@yandex.ru");
        account.addUser(new User("Bogomolov Petr", "Rus"));
        account.addEmail("Bogomolov Petr", "@yandex.ru");
        account.addEmail("Bogomolov Petr", "@google.com");
        account.addEmail("Bogomolov Petr", "@mail.ru");
        account.mergeAccount();
        Set<String> email = account.findEmail(account.findByName("Petr Bogomolov").get());
        Iterator<String> it = email.iterator();
        assertThat(it.next(), is("@google.com"));
        assertThat(it.next(), is("@mail.ru"));
        assertThat(it.next(), is("@yandex.ru"));
    }
}