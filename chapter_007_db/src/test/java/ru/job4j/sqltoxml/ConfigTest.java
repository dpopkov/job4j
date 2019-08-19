package ru.job4j.sqltoxml;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenInitThenLoadsPropertiesFromSpecifiedFile() {
        Config config = new Config();
        config.init();
        String url = config.get("url");
        assertThat(url, startsWith("jdbc:sqlite:"));
    }
}
