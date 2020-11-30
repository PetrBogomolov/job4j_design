package ru.job4j.it.simplearray;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddSuccessfullThenIt() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddSimilarElements() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }
}