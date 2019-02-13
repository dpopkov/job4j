package ru.job4j.search;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
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
}
