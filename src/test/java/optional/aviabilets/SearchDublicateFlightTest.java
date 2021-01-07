package optional.aviabilets;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchDublicateFlightTest {

    private SearchDublicateFlight search;
    private String flight1;
    private String flight2;
    private boolean result;

    @Before
    public void setup() {
        search = new SearchDublicateFlight();
    }

    @Test
    public void whenFlightEqualsCase1() {
        flight1 = "AFL1";
        flight2 = "AFL0001";
        result = search.searchDublicate(flight1, flight2);
        assertThat(true, is(result));
    }

    @Test
    public void whenFlightEqualsCase2() {
        flight1 = "D2 25";
        flight2 = "D225";
        result = search.searchDublicate(flight1, flight2);
        assertThat(true, is(result));
    }

    @Test
    public void whenFlightEqualsCase3() {
        flight1 = "D2 25";
        flight2 = "D2025";
        result = search.searchDublicate(flight1, flight2);
        assertThat(true, is(result));
    }

    @Test
    public void whenFlightNotEquals() {
        flight1 = "D2 24";
        flight2 = "D2025";
        result = search.searchDublicate(flight1, flight2);
        assertThat(false, is(result));
    }
}