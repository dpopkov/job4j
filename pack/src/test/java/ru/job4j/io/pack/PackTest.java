package ru.job4j.io.pack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class PackTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenDirectorySuppliedThenArchiveCreated() throws IOException {
        File testDir = temporaryFolder.newFolder("testDir");
        File projectDir = new File(testDir, "project");
        if (projectDir.mkdir()) {
            Files.writeString(projectDir.toPath().resolve("file.txt"), "test");
            Pack.main(new String[]{"-d", projectDir.getAbsolutePath()});
        }
        assertThat(Files.exists(testDir.toPath().resolve("project.zip")), is(true));
    }
}
