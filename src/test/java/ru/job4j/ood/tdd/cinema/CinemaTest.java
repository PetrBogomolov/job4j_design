package ru.job4j.ood.tdd.cinema;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CinemaTest {

    @Test
    public void whenFindSessions() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Collections.singletonList(new Session3D())));
    }

    @Test
    public void whenNotFindSessions() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions.size(), is(0));
    }

    @Test
    public void whenBuySuccessful() {
        Account account = new AccountCinema3D();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceSold() {
        Account account = new AccountCinema3D();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar();
        cinema.buy(account, 1, 1, date);
        cinema.buy(account, 1, 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceNotExist() {
        Account account = new AccountCinema3D();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar();
        cinema.buy(account, - 1, - 1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDateInvalid() {
        Account account = new AccountCinema3D();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.FEBRUARY, 18);
        cinema.buy(account, 1, 1, date);
    }

    @Test
    public void whenTicketsAreOut() {
        Account account = new AccountCinema3D();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertNull(ticket);
    }
}
