package ru.job4j.sqltoxml;

import ru.job4j.tools.CmdInput;
import ru.job4j.tools.Resources;

import java.nio.file.Paths;

public class Main {
    private static final String XSLT = "xml/ConvertXslt.transform.xml";

    public static void main(String[] args) {
        String xmlFilename = "default.xml";
        if (args.length > 0) {
            xmlFilename = args[0];
        }
        final int size = CmdInput.nextInt("Enter number of entries: ");
        long sum = new EntryProcessor(size, Paths.get(xmlFilename), Resources.getPath(XSLT)).start();
        System.out.println("Result: sum = " + sum);
    }
}
