package ru.job4j.it.mail;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UniqueAccountTest {
    private UniqueAccount uniqueAccount;
    private User user1;
    private User user2;
    private String yandex;
    private String google;
    private Map<User, Set<String>> accounts;

    @Before
    public void setup() {
        uniqueAccount = new UniqueAccount();
        user1 = new User("User1", "Rus");
        user2 = new User("User2", "Rus");
        yandex = "user@yandex.ru";
        google = "user@google.com";
        accounts = new HashMap<>();
    }

    @Test
    public void whenEmailEqualsThenMerge() {
        accounts.put(user1, new HashSet<>(Set.of(yandex)));
        accounts.put(user2, new HashSet<>(Set.of(yandex, google)));
        var result = uniqueAccount.mergeAccount(accounts);
        Set<String> emails = result.get(user1);
        Iterator<String> it = emails.iterator();
        assertThat(it.next(), is(google));
        assertThat(it.next(), is(yandex));
    }

    @Test (expected = NullPointerException.class)
    public void whenMergeSuccessfullyThenExcessUserDelete() {
        accounts.put(user1, new HashSet<>(Set.of(yandex)));
        accounts.put(user2, new HashSet<>(Set.of(yandex)));
        var result = uniqueAccount.mergeAccount(accounts);
        Set<String> emails = result.get(user2);
        Iterator<String> it = emails.iterator();
        it.next();
    }

    @Test
    public void whenEmailNotEqualsThenNotMerge() {
        accounts.put(user1, new HashSet<>(Set.of(yandex)));
        accounts.put(user2, new HashSet<>(Set.of(google)));
        var result = uniqueAccount.mergeAccount(accounts);
        Set<String> emailsUser1 = result.get(user1);
        Set<String> emailsUser2 = result.get(user2);
        Iterator<String> itUser1 = emailsUser1.iterator();
        Iterator<String> itUser2 = emailsUser2.iterator();
        assertThat(itUser1.next(), is(yandex));
        assertThat(itUser2.next(), is(google));
    }
}