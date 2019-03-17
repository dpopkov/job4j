package ru.job4j.io.find;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListWriterTest {
    private static TestDirWrapper testDirWrapper;
    private static Path testDir;

    @BeforeClass
    public static void setupTestDir() throws IOException {
        testDirWrapper = new TestDirWrapper();
        testDir = testDirWrapper.getPath();
    }

    @Test
    public void whenWriteLinesToFileThenFileContainsLines() throws IOException {
        Path file = testDir.resolve("lines.txt");
        ListWriter writer = new ListWriter(file);
        writer.write(List.of("line1", "строка2"));
        var readLines = Files.readAllLines(file);
        assertThat(readLines, is(List.of("line1", "строка2")));
    }

    @AfterClass
    public static void cleanTestDir() throws IOException {
        testDirWrapper.clean();
    }
}
