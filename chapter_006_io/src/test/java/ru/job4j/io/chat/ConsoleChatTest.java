package ru.job4j.io.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ConsoleChatTest {
    private static final String NL = System.lineSeparator();
    private static final long TEST_SEED = 4096L;

    private final List<String> phrases = List.of("phrase1", "phrase2");
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private Path testDir;
    private Path input;
    private InputStream savedIn;
    private PrintStream savedOut;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        testDir = temporaryFolder.newFolder("testDir").toPath();
        input = Files.write(testDir.resolve("input.txt"), phrases, StandardCharsets.UTF_8);
        savedIn = System.in;
        savedOut = System.out;
        System.setOut(new PrintStream(buffer, true, StandardCharsets.UTF_8));
    }

    @Test
    public void whenStartChatThenInputIsPrintedAndLogged() throws IOException {
        String userInput = String.join(NL, "Hello", "Goodbye", "quit", "");
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));
        Path logFile = testDir.resolve("log.txt");
        ConsoleChat chat = new ConsoleChat(input, logFile);
        chat.setRandom(new Random(TEST_SEED));
        chat.start();
        String botResponse = String.join(NL, "phrase1", "phrase2", "");
        String conversation = String.join(NL, "Hello", "phrase1", "Goodbye", "phrase2", "quit", "");
        assertThat(buffer.toString(), is(botResponse));
        String log = Files.readString(logFile, StandardCharsets.UTF_8);
        assertThat(log, is(conversation));
    }

    @After
    public void restoreInOut() {
        System.setIn(savedIn);
        System.setOut(savedOut);
    }
}
