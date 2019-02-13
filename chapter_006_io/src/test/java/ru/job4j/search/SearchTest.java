package ru.job4j.search;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class SearchTest {
    private final List<File> expectedFiles = new ArrayList<>();
    private File testDir;

    /** Allows creation of files and folders that will be deleted when the test finishes. */
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        testDir = temporaryFolder.newFolder("testDir");
        File dir1 = makeDir(testDir, "dir1");
        File dir2 = makeDir(testDir, "dir2");
        File dir3 = makeDir(testDir, "dir3");
        File dir34 = makeDir(dir3, "dir34");
        makeFileExpected(dir1, "txt");
        makeFileExpected(dir2, "java");
        makeFile(dir2, "bak");
        makeFileExpected(dir3, "java");
        makeFile(dir3, "class");
        makeFileExpected(dir34, "java");
        makeFile(dir34, "doc");
    }

    @Test
    public void whenSearchFilesWithGivenExtensionsThenReturnsCorrectList() {
        List<File> result = new Search().files(testDir.getAbsolutePath(), List.of("txt", "java"));
        assertThat(result, is(expectedFiles));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static File makeDir(File parent, String name) {
        File d = new File(parent, name);
        d.mkdir();
        return d;
    }

    private static File makeFile(File parent, String ext) throws IOException {
        return File.createTempFile("test", "." + ext, parent);
    }

    private void makeFileExpected(File parent, String ext) throws IOException {
        expectedFiles.add(makeFile(parent, ext));
    }
}
