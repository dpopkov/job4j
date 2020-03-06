package ru.job4j.ood.templates;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleGeneratorTest {
    private final Template template = new SimpleGenerator();

    @Test
    public void whenGenerateUsingOneValueThenValueReplacesKey() {
        String templateText = "Hello, ${name}";
        String result = template.generate(templateText, Map.of("name", "Alice"));
        assertThat(result, is("Hello, Alice"));
    }

    @Test
    public void whenGenerateUsingTwoValuesThenValuesReplaceKeys() {
        String templateText = "${greeting}! I am ${name}, who are ${subject}?";
        String result = template.generate(templateText,
                Map.of("greeting", "Hi", "name", "Bob", "subject", "you"));
        assertThat(result, is("Hi! I am Bob, who are you?"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeysThenThrowsException() {
        String templateText = "no template";
        template.generate(templateText, Map.of("key", "value"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExcessiveKeysThenThrowsException() {
        String templateText = "${one} ${two}";
        String s = template.generate(templateText, Map.of("one", "first value"));
        System.out.println(s);
    }

    @Test
    public void whenValueIsNullThenSubstitutesNullForKey() {
        String templateText = "this is ${key}";
        Map<String, String> map = new HashMap<>();
        map.put("key", null);
        String result = template.generate(templateText, map);
        assertThat(result, is("this is null"));
    }
}
