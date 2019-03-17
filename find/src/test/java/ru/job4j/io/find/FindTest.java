package ru.job4j.io.find;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindTest {
    @Test
    public void whenMainWithInvalidArgumentsThenPrintUsage() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        Find.main(new String[] {"--non-existing"});
        String expected = String.join(System.lineSeparator(),
                "Usage: java -jar find.jar [-d directory] -n name [-m | -f | -r] [-o output]",
                "",
                "-d directory    directory where the search starts",
                "-n name         name of the file or pattern for other types of search",
                "-m              search by mask",
                "-f              search by full name",
                "-r              search by regular expression",
                "-o output       name of the result file",
                "",
                "default starting directory is the current directory",
                "default search is by full name",
                "default result file is 'log.txt'",
                "");
        assertThat(buffer.toString(), is(expected));
    }

    @Test
    public void whenMainCalledWithProperArgumentsThenFilesAreFoundAndResultSaved() throws IOException {
        TestDirWrapper testDirWrapper = new TestDirWrapper(true);
        Path testDir = testDirWrapper.getPath();
        Path dir1 = Files.createTempDirectory(testDir, "dir1");
        Path dir2 = Files.createTempDirectory(testDir, "dir2");
        Path file1 = Files.createFile(dir1.resolve("test1.xml"));
        Path file2 = Files.createFile(dir2.resolve("test2.txt"));
        Path log = testDir.resolve("log.txt");
        String[] args = {
                "-d", testDir.toAbsolutePath().toString(),
                "-r", "-n", "test\\d\\.(xml|txt)",
                "-o", log.toAbsolutePath().toString()
        };
        Find.main(args);
        assertThat(Files.exists(log), is(true));
        List<String> result = Files.readAllLines(log);
        assertThat(result.size(), is(2));
        assertThat(result, hasItems(file1.toString(), file2.toString()));
        testDirWrapper.clean();
    }
}
