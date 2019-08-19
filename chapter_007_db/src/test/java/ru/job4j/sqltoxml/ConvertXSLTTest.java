package ru.job4j.sqltoxml;

import org.junit.Test;
import ru.job4j.tools.Resources;


import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConvertXSLTTest {

    @Test
    public void whenConvertThenSourceFileIsConvertedUsingXslTransformation() throws Exception {
        Path testDir = Files.createTempDirectory(ConvertXSLTTest.class.getName());
        Path dest = testDir.resolve("ConvertXslt.output.xml");
        Path source = Resources.getPath("xml/ConvertXslt.input.xml");
        Path xslt = Resources.getPath("xml/ConvertXslt.transform.xml");
        String expected = String.join(System.lineSeparator(),
                "<entries>",
                "    <entry field=\"10\"/>",
                "    <entry field=\"20\"/>",
                "</entries>",
                "");
        ConvertXSLT converter = new ConvertXSLT();
        try {
            converter.convert(source, dest, xslt);
            assertThat(Files.exists(dest), is(true));
            String transformedXml = Files.readString(dest);
            assertThat(transformedXml, is(expected));
        } finally {
            Files.deleteIfExists(dest);
            Files.deleteIfExists(testDir);
        }
    }
}
