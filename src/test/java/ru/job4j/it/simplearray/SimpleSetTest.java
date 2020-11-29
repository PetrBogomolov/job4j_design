package ru.job4j.it.simplearray;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddSuccessfull() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        assertThat(set.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(2);
        set.get(1);
    }

    @Test
    public void whenAddThenIt() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }
}