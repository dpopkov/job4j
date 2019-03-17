package ru.job4j.io.find;

import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ArgsTest {
    @Test
    public void whenArgsContainAllValuesThenReturnAllArgumentValues() {
        Args args = new Args(splitArguments("-d c:\\ -n *.txt -m -o result.txt"));
        assertArgs(args, "c:\\", "*.txt", SearchBy.MASK, "result.txt");
    }

    @Test
    public void whenArgsContainAllValuesInRandomOrderThenReturnAllArgumentValues() {
        Args args = new Args(splitArguments("-r -n [a-z]+\\d?.\\w{2,4} -o result.txt -d c:\\test"));
        assertArgs(args, "c:\\test", "[a-z]+\\d?.\\w{2,4}", SearchBy.REGEX, "result.txt");
    }

    @Test
    public void whenArgsDoNotContainAllValuesThenReturnDefaultArgumentValues() {
        Args args = new Args(splitArguments("-n *.txt"));
        assertArgs(args, System.getProperty("user.dir"), "*.txt", SearchBy.FULL, "log.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsWithNonCorrectArgumentsThenThrowException() {
        new Args(splitArguments("-z result.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgsWithoutArgumentsThenThrowException() {
        new Args(new String[0]);
    }

    private void assertArgs(Args args, String directory, String name, SearchBy searchBy, String output) {
        assertThat(args.getDirectory().toAbsolutePath().normalize(),
                is(Paths.get(directory).toAbsolutePath().normalize()));
        assertThat(args.getName(), is(name));
        assertThat(args.getSearchBy(), is(searchBy));
        assertThat(args.getOutput(), is(output));
    }

    private String[] splitArguments(String args) {
        return args.split("\\s+");
    }
}
