package ru.job4j.collectionpro.simplearray;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenArrayIncreases() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.add("second");
        array.add("third");
        array.add("four");
        String rsl = array.get(3);
        assertThat(rsl, is("four"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.add("second");
        Iterator<String> it = array.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}