package ru.job4j.io.pack;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;

public class FolderArchiverTest {
    private static final String PROJECT_NAME = "project1";
    private static final String SOURCE_FOLDER = PROJECT_NAME;
    private static final String OUTPUT_FILE = PROJECT_NAME + ".zip";

    private final Map<String, Long> expectedInZip = new HashMap<>();
    private Path source;
    private Path dir1;
    private Path dir11;

    @Before
    public void setUp() throws IOException {
        Path testDir = Jimfs.newFileSystem(Configuration.unix()).getPath("testDir");
        Files.createDirectory(testDir);
        source = Files.createDirectory(testDir.resolve(SOURCE_FOLDER));
        dir1 = Files.createDirectory(source.resolve("dir1"));
        dir11 = Files.createDirectory(dir1.resolve("dir11"));
    }

    @Test
    public void whenCompressWithoutLimitationsThenArchiveContainsAllFiles() throws IOException {
        makeFile(expectedInZip, source, "File1.java",
                "public class File1 { public void bar() {} }");
        makeFile(expectedInZip, dir1, "file11.xml", "<xml><content>content 11</content></xml>");
        makeFile(expectedInZip, dir11, "File111.java", "public class File111 { public void bar() {} }");
        makeFile(expectedInZip, dir11, "file112.txt",
                "A quick brown fox jumps over a lazy dog. A quick brown fox jumps over a lazy dog.");
        Path zipArchive = new FolderArchiver().compress(source, source.resolveSibling(OUTPUT_FILE), null);
        assertThat(Files.exists(zipArchive), is(true));
        Map<String, Long> zippedFiles = putZipEntriesInfoToMap(zipArchive);
        assertThat(zippedFiles, is(expectedInZip));
    }

    @Test
    public void whenCompressExcludingByExtensionThenArchiveDoesNotContainExcludedFiles() throws IOException {
        makeFile(expectedInZip, source, "File1.java",
                "public class File1 { public void bar() {} }");
        makeFile(null, dir1, "file11.xml", "<xml><content>content 11</content></xml>");
        makeFile(expectedInZip, dir11, "File111.java", "public class File111 { public void bar() {} }");
        makeFile(null, dir11, "file112.txt",
                "A quick brown fox jumps over a lazy dog. A quick brown fox jumps over a lazy dog.");
        Path zipArchive = new FolderArchiver().compress(source, source.resolveSibling(OUTPUT_FILE), List.of("xml", "txt"));
        assertThat(Files.exists(zipArchive), is(true));
        Map<String, Long> zippedFiles = putZipEntriesInfoToMap(zipArchive);
        assertThat(zippedFiles, is(expectedInZip));
    }

    private static Map<String, Long> putZipEntriesInfoToMap(Path zipFile) throws IOException {
        Map<String, Long> info = new HashMap<>();
        try (ZipInputStream zin = new ZipInputStream(Files.newInputStream(zipFile))) {
            ZipEntry entry;
            Checker checker = new Checker();
            while ((entry = zin.getNextEntry()) != null) {
                info.put(entry.getName(), checker.crc32(zin));
                zin.closeEntry();
            }
        }
        return info;
    }

    private void makeFile(Map<String, Long> map, Path parent, String name, String content) throws IOException {
        Path file = parent.resolve(name);
        Files.writeString(file, content);
        if (map != null) {
            Checker checker = new Checker();
            map.put(source.relativize(file).toString(), checker.crc32(file));
        }
    }

    private static class Checker {
        private final byte[] bytes = new byte[128];

        public long crc32(InputStream in) throws IOException {
            Checksum checksum = new CRC32();
            CheckedInputStream checkedIn = new CheckedInputStream(in, checksum);
            int n;
            do {
                n = checkedIn.read(bytes);
            } while (n != -1);
            return checksum.getValue();
        }

        public long crc32(Path file) throws IOException {
            Checksum checksum = new CRC32();
            checksum.update(Files.readAllBytes(file));
            return checksum.getValue();
        }
    }
}
