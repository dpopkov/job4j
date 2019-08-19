package ru.job4j.sqltoxml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Contains methods that perform calculations using input received from XML files.
 */
public class SAXCalculator {
    private final String fieldName;
    private final String attrName;

    public SAXCalculator(String fieldName, String attrName) {
        this.fieldName = fieldName;
        this.attrName = attrName;
    }

    /**
     * Calculates the total sum of field values in the specified XML file.
     * @param path path to XML file
     * @return total sum
     */
    public long sum(Path path) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        TotalSumHandler handler = new TotalSumHandler();
        parser.parse(Files.newInputStream(path), handler);
        return handler.getTotal();
    }

    private class TotalSumHandler extends DefaultHandler {
        private long total;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (fieldName.equals(qName)) {
                String attrValue = attributes.getValue(attrName);
                if (attrValue != null) {
                    total += Long.parseLong(attrValue);
                }
            }
        }

        public long getTotal() {
            return total;
        }
    }
}
