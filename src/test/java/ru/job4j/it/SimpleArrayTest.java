package ru.job4j.it;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<Integer> array;

    @Before
    public void setup() {
        array = new SimpleArray(3);
    }

    @Test
    public void whenAddSuccessfull() {
        array.add(1);
        array.add(2);
        array.add(3);
        Iterator<Integer> it = array.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenExceptionByAdd() {
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
    }

    @Test
    public void whenSetSuccessfull() {
        array.add(1);
        array.add(1);
        array.add(3);
        array.set(1, 2);
        Iterator<Integer> it = array.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExceptionBySet() {
        array.add(1);
        array.add(1);
        array.add(3);
        array.set(5, 2);
    }

    @Test
    public void whenRemoveSuccessfull() {
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(1);
        Iterator<Integer> it = array.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExceptionByRemove() {
        array.add(1);
        array.add(2);
        array.add(3);
        array.remove(4);
    }

    @Test
    public void whenGetSuccessfull() {
        array.add(11);
        array.add(12);
        array.add(13);
        assertThat(array.get(1), is(12));
    }

    @Test
    public void whenGetNullThenNull() {
        array.add(11);
        array.add(12);
        array.add(null);
        assertNull(array.get(2));
    }
}