package ru.job4j.sqltoxml;

import org.junit.Test;
import ru.job4j.tools.Resources;

import java.nio.file.Path;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SAXCalculatorTest {

    @Test
    public void whenSumThenReturnsTotalSumOfValues() throws Exception {
        SAXCalculator calc = new SAXCalculator("entry", "field");
        final String xmlName = "xml/SAXCalculator.input.xml";
        Path path = Resources.getPath(xmlName);
        long result = calc.sum(path);
        assertThat(result, is(33L));
    }
}
