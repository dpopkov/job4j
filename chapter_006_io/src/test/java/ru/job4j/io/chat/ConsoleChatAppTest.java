package ru.job4j.io.chat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class ConsoleChatAppTest {
    /**
     * Temporary folder in default file system is used to
     * test paths passed as normal strings.
     */
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenMainCalledWithStringPathsThenLogCreated() throws IOException {
        Path testDir = temporaryFolder.newFolder("testDir").toPath();
        String userInput = "quit" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Path input = Files.write(testDir.resolve("input.txt"), List.of("Привет"), StandardCharsets.UTF_8);
        Path log = testDir.resolve("log.txt");
        String[] args = {input.toAbsolutePath().toString(), log.toAbsolutePath().toString()};
        ConsoleChatApp.main(args);
        assertTrue(Files.exists(log));
    }
}
