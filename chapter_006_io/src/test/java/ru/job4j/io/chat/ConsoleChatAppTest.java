package ru.job4j.io.chat;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ConsoleChatAppTest {
    @Test
    public void whenMainCalledWithStringPathsThenLogCreated() throws IOException {
        TemporaryFolder temporaryFolder = new TemporaryFolder();
        temporaryFolder.create();
        Path testDir = temporaryFolder.newFolder("testDir").toPath();
        String userInput = "quit" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Path input = Files.write(testDir.resolve("input.txt"), List.of("Привет"), StandardCharsets.UTF_8);
        Path log = testDir.resolve("log.txt");
        String[] args = {input.toAbsolutePath().toString(), log.toAbsolutePath().toString()};
        ConsoleChatApp.main(args);
        assertTrue(Files.exists(log));
        temporaryFolder.delete();
    }

    @Test
    public void whenMainCalledWithEmptyArgsThenPrintMessage() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        ConsoleChatApp.main(new String[0]);
        assertThat(buffer.toString(), is("Usage: java ConsoleChatApp inputFile logFile" + System.lineSeparator()));
    }
}
