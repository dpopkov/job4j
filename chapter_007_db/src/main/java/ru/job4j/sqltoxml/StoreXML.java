package ru.job4j.sqltoxml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Stores entries in an XML file using JAXB.
 */
public class StoreXML {
    private final Path target;

    public StoreXML(Path target) {
        this.target = target;
    }

    /**
     * Saves entries to the target file.
     * @param entries list of entries
     */
    public void save(List<Entry> entries) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        Entries wrapper = new Entries(entries);
        try (BufferedWriter out = Files.newBufferedWriter(target)) {
            marshaller.marshal(wrapper, out);
        }
    }
}
