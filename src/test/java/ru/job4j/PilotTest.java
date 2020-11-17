package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class PilotTest {

    @Test
    public void print() {
        assertEquals("Java", Pilot.print());
    }
}