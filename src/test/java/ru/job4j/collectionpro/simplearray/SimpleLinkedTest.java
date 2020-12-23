package ru.job4j.collectionpro.simplearray;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedTest {
    private SimpleLinked<Integer> linked;

    @Before
    public void setup() {
        linked = new SimpleLinked();
    }

    @Test
    public void whenAddInEndSuccessfull() {
        linked.addInEnd(12);
        Integer result = linked.get(0);
        assertThat(result, is(12));
    }

    @Test
    public void whenAddThenIt() {
        linked.addInEnd(1);
        linked.addInEnd(3);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        linked.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        linked.addInEnd(10);
        linked.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        linked.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        linked.addInEnd(1);
        Iterator<Integer> it = linked.iterator();
        linked.addInEnd(2);
        it.next();
    }
}