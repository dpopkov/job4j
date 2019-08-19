package ru.job4j.sqltoxml;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class StoreXMLTest {

    @Test
    public void whenSaveThenWritesXml() throws IOException, JAXBException {
        Path testDir = Files.createTempDirectory(StoreXMLTest.class.getName());
        Path target = testDir.resolve("store.xml");
        StoreXML storeXML = new StoreXML(target);
        List<Entry> entries = Arrays.asList(new Entry(10), new Entry(20));
        String expected = String.join("\n",
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<entries>",
                "    <entry>",
                "        <field>10</field>",
                "    </entry>",
                "    <entry>",
                "        <field>20</field>",
                "    </entry>",
                "</entries>",
                "");
        try {
            storeXML.save(entries);
            assertThat(Files.exists(target), is(true));
            String actual = Files.readString(target);
            assertThat(actual, is(expected));
        } finally {
            Files.deleteIfExists(target);
            Files.deleteIfExists(testDir);
        }
    }
}
