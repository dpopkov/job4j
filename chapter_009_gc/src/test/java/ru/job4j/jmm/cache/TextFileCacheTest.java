package ru.job4j.jmm.cache;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TextFileCacheTest {
    private static final String NL = System.lineSeparator();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testGetContents() throws IOException {
        String names = "Alice" + NL + "Bob";
        String address = "Bob's address";
        createFile("names.txt", names);
        createFile("address.txt", address);
        TextFileCache textCache = new TextFileCache(temporaryFolder.getRoot().toPath());
        String namesResult = textCache.getContents("names.txt");
        assertThat(namesResult, is(names));
        String addressResult = textCache.getContents("address.txt");
        assertThat(addressResult, is(address));
    }

    private void createFile(String name, String contents) throws IOException {
        File f = temporaryFolder.newFile(name);
        Files.writeString(f.toPath(), contents);
    }
}
