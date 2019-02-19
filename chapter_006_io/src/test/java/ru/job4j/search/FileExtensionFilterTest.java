package ru.job4j.search;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class FileExtensionFilterTest {

    @Test
    public void whenAcceptFileWithExistingExtensionThenTrue() {
        FileFilter filter = new FileExtensionFilter(List.of("txt"));
        assertThat(filter.accept(new File("dir/1.txt")), is(true));
    }

    @Test
    public void whenAcceptFileWithNonExistingExtensionThenFalse() {
        FileFilter filter = new FileExtensionFilter(List.of("class, xls"));
        assertThat(filter.accept(new File("1.doc")), is(false));
    }

    @Test
    public void whenFilterWithNullParameterThenAcceptsAllFiles() {
        FileFilter filter = new FileExtensionFilter(null);
        assertThat(filter.accept(new File("1.doc")), is(true));
        assertThat(filter.accept(new File("2.txt")), is(true));
        assertThat(filter.accept(new File("3.tmp")), is(true));
    }

    @Test
    public void whenAcceptPathWithExistingExtensionThenTrue() {
        FileExtensionFilter filter = new FileExtensionFilter(List.of("txt"));
        assertThat(filter.accept(Paths.get("dir/1.txt")), is(true));
    }

    @Test
    public void whenAcceptPathWithNonExistingExtensionThenFalse() {
        FileExtensionFilter filter = new FileExtensionFilter(List.of("class, xls"));
        assertThat(filter.accept(Paths.get("1.doc")), is(false));
    }

    @Test
    public void whenFilterWithNullParameterThenAcceptsAllPaths() {
        FileExtensionFilter filter = new FileExtensionFilter(null);
        assertThat(filter.accept(Paths.get("1.doc")), is(true));
        assertThat(filter.accept(Paths.get("2.txt")), is(true));
    }

    @Test
    public void whenRejectPathWithExistingExtensionThenFalse() {
        FileExtensionFilter filter = new FileExtensionFilter(List.of("txt"));
        assertThat(filter.reject(Paths.get("dir/1.txt")), is(false));
    }

    @Test
    public void whenRejectPathWithNonExistingExtensionThenTrue() {
        FileExtensionFilter filter = new FileExtensionFilter(List.of("txt"));
        assertThat(filter.reject(Paths.get("dir/1.doc")), is(true));
    }

    @Test
    public void whenFilterWithNullParameterThenRejectAllPaths() {
        FileExtensionFilter filter = new FileExtensionFilter(null);
        assertThat(filter.reject(Paths.get("1.doc")), is(false));
        assertThat(filter.reject(Paths.get("2.txt")), is(false));
    }
}
