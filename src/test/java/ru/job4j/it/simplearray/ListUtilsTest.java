package ru.job4j.it.simplearray;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBeforeSuccessfull() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterSuccessfull() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3, 4));
        ListUtils.addAfter(input, 1, 2);
        assertThat(Arrays.asList(1, 3, 2, 4), is(input));
    }

    @Test
    public void whenRemoveIfSuccessfull() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> isEvenNumber = x -> x % 2 == 0;
        var result = ListUtils.removeIf(input, isEvenNumber);
        assertThat(Arrays.asList(1, 3), is(result));
    }

    @Test
    public void whenReplaceIfSuccessfull() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> ifNumberTwo = x -> x == 2;
        var result = ListUtils.replaceIf(input, ifNumberTwo, 5);
        assertThat(Arrays.asList(1, 5, 3, 4), is(result));
    }

    @Test
    public void whenRemoveAllSuccessfull() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> delet = new ArrayList<>(Arrays.asList(3, 2));
        var result = ListUtils.removeAll(input, delet);
        assertThat(Arrays.asList(1, 4), is(result));
    }
}