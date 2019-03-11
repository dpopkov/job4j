package ru.job4j.io.netw.bot;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DataLoaderTest {
    @Test
    public void whenLoadFromXmlThenFillMapWithData() throws IOException {
        String xml = String.join("\n",
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
                "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">",
                "<properties><entry key=\"test-key\">test-value</entry></properties>"
        );
        DataLoader loader = new DataLoader();
        loader.loadFromXML(new ByteArrayInputStream(xml.getBytes()));
        Map<String, String> data = loader.getMap();
        assertNotNull(data);
        assertThat(data.get("test-key"), Is.is("test-value"));
    }
}
