package ru.job4j.io.chat;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SimpleLoggerTest {

    @Test
    public void whenAcceptMessageThenMessageIsWrittenToFile() throws IOException {
        Path testDir = Jimfs.newFileSystem(Configuration.unix()).getPath("testDir");
        Files.createDirectory(testDir);
        Path log = testDir.resolve("log.txt");
        SimpleLogger logger = new SimpleLogger(log);
        logger.accept("test1");
        assertTrue(Files.exists(log));
        assertThat(Files.readString(log), is("test1" + System.lineSeparator()));
    }
}
