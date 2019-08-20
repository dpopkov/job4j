package ru.job4j.sqltoxml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

/**
 * Generates entries, saves entries to XML, transforms XML, and uses SAX parser to parse the result.
 */
public class EntryProcessor {
    private static final Logger LOG = LogManager.getLogger(EntryProcessor.class);

    private final Path xmlPath;
    private final Path xsltPath;
    private final int size;

    /** Initializes processor with the number of entries, path to XML and XSLT files. */
    public EntryProcessor(int size, Path xmlPath, Path xsltPath) {
        this.size = size;
        this.xmlPath = xmlPath;
        this.xsltPath = xsltPath;
    }

    /** Starts the processing and returns result. */
    public long start() {
        long result = -1L;
        List<Entry> entries = saveDbAndLoadEntries();
        boolean saved = saveToXml(entries);
        if (saved) {
            Path converted = convertXml();
            if (converted != null) {
                result = parseXmlSum(converted);
            }
        }
        return result;
    }

    /** Generates entries in the db and returns list of entries. */
    private List<Entry> saveDbAndLoadEntries() {
        Config config = new Config();
        config.init();
        StoreSQL store;
        try {
            store = new StoreSQL(config);
            store.generate(size);
            LOG.info("Generated {} entries", size);
            return store.load();
        } catch (SQLException e) {
            LOG.error("Cannot save or load entries", e);
            return List.of();
        }
    }

    /** Saves entries to XML file. */
    private boolean saveToXml(List<Entry> entries) {
        StoreXML storeXML = new StoreXML(xmlPath);
        try {
            storeXML.save(entries);
            LOG.info("Saved XML to {}", xmlPath);
            return true;
        } catch (JAXBException | IOException e) {
            LOG.error("Cannot store XML", e);
            return false;
        }
    }

    /** Converts XML and returns path to the converted XML file. */
    private Path convertXml() {
        String filename = xmlPath.getFileName().toString();
        String convertedName = filename.substring(0, filename.indexOf(".xml")) + "_transformed.xml";
        Path convertedXml = xmlPath.resolveSibling(convertedName);
        ConvertXSLT converter = new ConvertXSLT();
        try {
            converter.convert(xmlPath, convertedXml, xsltPath);
            LOG.info("Converted XML saved to {}", convertedXml);
            return convertedXml;
        } catch (IOException | TransformerException e) {
            LOG.error("Cannot convert XML", e);
            return null;
        }
    }

    /** Parses XML file and returns result. */
    private long parseXmlSum(Path xmlPath) {
        SAXCalculator calc = new SAXCalculator("entry", "field");
        try {
            return calc.sum(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOG.error("Cannot parse XML", e);
            return -1;
        }
    }
}
