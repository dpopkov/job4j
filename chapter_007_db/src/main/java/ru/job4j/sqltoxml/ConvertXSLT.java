package ru.job4j.sqltoxml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Contains methods for transforming XML files using XSLT.
 */
public class ConvertXSLT {
    /**
     * Converts the source file using the specified XSLT document.
     * @param source source file
     * @param dest destination file
     * @param xslt XSLT document
     */
    public void convert(Path source, Path dest, Path xslt) throws IOException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer;
        try (Reader xsltInput = Files.newBufferedReader(xslt)) {
            transformer = factory.newTransformer(new StreamSource(xsltInput));
        }
        try (Reader in = Files.newBufferedReader(source);
             Writer out = Files.newBufferedWriter(dest)) {
            transformer.transform(new StreamSource(in), new StreamResult(out));
        }
    }
}
