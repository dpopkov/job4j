package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleLoggerTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenAcceptMessageThenMessageIsWrittenToFile() throws IOException {
        Path testDir = temporaryFolder.newFolder("testDir").toPath();
        Path log = testDir.resolve("log.txt");
        SimpleLogger logger = new SimpleLogger(log);
        logger.accept("test1");
        assertTrue(Files.exists(log));
        assertThat(Files.readString(log), is("test1" + System.lineSeparator()));
    }
}
