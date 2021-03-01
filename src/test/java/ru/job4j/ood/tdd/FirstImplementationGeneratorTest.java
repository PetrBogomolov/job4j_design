package ru.job4j.ood.tdd;

import org.junit.Before;
import org.junit.Test;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FirstImplementationGeneratorTest {

    private Map<String, String> map;
    private String template;
    private String templateWithExtraKey;
    private String templateWithSmallerKeyThanInMap;

    @Before
    public void setup() {
        map = Map.of(
                "name", "Petr",
                "subject", "you"
        );
        template = "I am a ${name}, Who are ${subject}?";
        templateWithExtraKey =
                "I am a ${name}, Who are ${subject} and Where are you ${preposition}?";
        templateWithSmallerKeyThanInMap = "I am a ${name}";
    }

    @Test
    public void whenGenerationWasSuccessful() {
        Generator generator = new FirstImplementationGenerator();
        String result = generator.produce(template, map);
        assertThat(result, is("I am a Petr, Who are you?"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapDontContainKey() {
        Generator generator = new FirstImplementationGenerator();
        generator.produce(templateWithExtraKey, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapContainsExtraKeys() {
        Generator generator = new FirstImplementationGenerator();
        generator.produce(templateWithSmallerKeyThanInMap, map);
    }
}
