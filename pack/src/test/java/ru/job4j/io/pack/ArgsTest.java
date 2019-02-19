package ru.job4j.io.pack;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ArgsTest {
    @Test
    public void whenAllCmdArgumentsSuppliedThenReturnsAllParameters() {
        Args args = new Args("-d c:\\project\\job4j\\ -e java xml -o project.zip".split(" "));
        assertThat(args.directory(), is("c:\\project\\job4j\\"));
        assertThat(args.exclude(), is(List.of("java", "xml")));
        assertThat(args.output(), is("project.zip"));
    }

    @Test
    public void whenAllCmdArgumentsShuffledThenReturnsAllParameters() {
        Args args = new Args("-o project.zip -d c:\\project\\job4j\\ -e java xml".split(" "));
        assertThat(args.directory(), is("c:\\project\\job4j\\"));
        assertThat(args.exclude(), is(List.of("java", "xml")));
        assertThat(args.output(), is("project.zip"));
    }

    @Test
    public void whenOutputNotSuppliedThenReturnDefaultOutput() {
        Args args = new Args("-d job4j -e doc class".split(" "));
        assertThat(args.output(), is("job4j.zip"));
        assertThat(args.exclude(), is(List.of("doc", "class")));
    }

    @Test
    public void whenExcludeNotSuppliedThenReturnEmptyList() {
        Args args = new Args("-d c:\\project\\job4j\\ -o c:\\tmp\\project.zip".split(" "));
        assertThat(args.directory(), is("c:\\project\\job4j\\"));
        assertThat(args.exclude(), is(List.of()));
        assertThat(args.output(), is("c:\\tmp\\project.zip"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoDirectoryThenThrowException() {
        new Args("-o c:\\tmp\\project.zip".split(" "));
    }
}
