package ru.job4j.it.statistic;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnalizeTest {
   private List<Analize.User> previous;
   private List<Analize.User> current;
   private Analize analize;
   private Analize.Info info;

    @Before
    public void setup() {
        analize = new Analize();
        previous = new ArrayList<>();
        current = new ArrayList<>();
    }

    @Test
    public void whenAddedTwoElements() {
       previous.add(new Analize.User(11, "name6"));
       current.add(new Analize.User(1, "name1"));
       current.add(new Analize.User(2, "name2"));
       info = analize.diff(previous, current);
       assertThat(info.getAdded(), is(2));
    }

    @Test
    public void whenDeleteTwoElements() {
        previous.add(new Analize.User(1, "name1"));
        previous.add(new Analize.User(2, "name2"));
        previous.add(new Analize.User(3, "name3"));
        current.add(new Analize.User(1, "name1"));
        current.add(new Analize.User(2, "name2"));
        info = analize.diff(previous, current);
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenChangeTwoElement() {
        previous.add(new Analize.User(1, "name1"));
        previous.add(new Analize.User(2, "name2"));
        current.add(new Analize.User(1, "name11"));
        current.add(new Analize.User(2, "name22"));
        info = analize.diff(previous, current);
        assertThat(info.getChanged(), is(2));
    }

    @Test
    public void whenAddedTwoElementsAndDeleteOneElementAndChangedOneElement() {
        previous.add(new Analize.User(3, "name3"));
        previous.add(new Analize.User(4, "name4"));
        current.add(new Analize.User(3, "name0"));
        current.add(new Analize.User(1, "name1"));
        current.add(new Analize.User(2, "name2"));
        info = analize.diff(previous, current);
        assertThat(info.getAdded(), is(2));
        assertThat(info.getDeleted(), is(1));
        assertThat(info.getChanged(), is(1));
    }
}