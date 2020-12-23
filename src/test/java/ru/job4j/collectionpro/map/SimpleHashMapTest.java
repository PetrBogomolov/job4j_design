package ru.job4j.collectionpro.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    private SimpleHashMap<String, String> hashMap;

    @Before
    public void setup() {
        hashMap = new SimpleHashMap();
    }

    @Test
    public void whenInsertSuccessfulThenTrue() {
        boolean result = hashMap.insert("key1", "value1");
        assertThat(true, is(result));
    }

    @Test
    public void whenInsertEqualsKeySuccessfulThenFalse() {
        hashMap.insert("key1", "value1");
        boolean result = hashMap.insert("key1", "value1");
        assertThat(false, is(result));
    }

    @Test
    public void whenGetElementSuccessfulThenValue() {
        hashMap.insert("key1", "value1");
        String result = hashMap.get("key1");
        assertThat("value1", is(result));
    }

    @Test
    public void whenGetElementNotSuccessfulThenNull() {
        hashMap.insert("key1", "value1");
        String result = hashMap.get("key2");
        assertNull(result);
    }

    @Test
    public void whenDeleteElementSuccessfulThenTrue() {
        hashMap.insert("key1", "value1");
        hashMap.insert("key2", "value2");
        hashMap.insert("key3", "value3");
        hashMap.delete("key2");
        Iterator<String> it = hashMap.iterator();
        assertThat(it.next(), is("value1"));
        assertThat(it.next(), is("value3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateEmptyHashMap() {
        hashMap.insert("key1", "value1");
        Iterator<String> it = hashMap.iterator();
        assertThat(it.next(), is("value1"));
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeHashMapWhileIterate() {
        hashMap.insert("key1", "value1");
        Iterator<String> it = hashMap.iterator();
        hashMap.insert("key2", "value2");
        it.next();
    }
}