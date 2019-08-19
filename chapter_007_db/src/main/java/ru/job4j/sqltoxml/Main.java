package ru.job4j.sqltoxml;

import org.xml.sax.SAXException;
import ru.job4j.tools.CmdInput;
import ru.job4j.tools.Resources;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String xmlFilename = "default.xml";
        if (args.length > 0) {
            xmlFilename = args[0];
        }
        final Path xmlPath = Paths.get(xmlFilename);
        final Path xsltPath = Resources.getPath("xml/ConvertXslt.transform.xml");
        final Path transformedXmlPath = xmlPath.resolveSibling(
                xmlFilename.substring(0, xmlFilename.indexOf(".xml")) + "_transformed.xml");
        final int size = CmdInput.nextInt("Enter size: ");

        try {
            List<Entry> entries = saveDbAndLoadEntries(size);
            saveToXml(xmlPath, entries);
            convertXml(xmlPath, xsltPath, transformedXmlPath);
            parseXmlSum(transformedXmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Entry> saveDbAndLoadEntries(int size) throws SQLException {
        Config config = new Config();
        config.init();
        StoreSQL store = new StoreSQL(config);
        store.generate(size);
        return store.load();
    }

    private static void convertXml(Path xmlPath, Path xslt, Path transformedPath) throws IOException, TransformerException {
        ConvertXSLT converter = new ConvertXSLT();
        converter.convert(xmlPath, transformedPath, xslt);
        System.out.println("Converted XML saved to " + transformedPath);
    }

    private static void saveToXml(Path xmlPath, List<Entry> list) throws JAXBException, IOException {
        StoreXML storeXML = new StoreXML(xmlPath);
        storeXML.save(list);
        System.out.println("XML saved to " + xmlPath);
    }

    private static void parseXmlSum(Path transformedPath) throws ParserConfigurationException, SAXException, IOException {
        SAXCalculator calc = new SAXCalculator("entry", "field");
        long sum = calc.sum(transformedPath);
        System.out.println("sum = " + sum);
    }
}
