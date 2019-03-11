package ru.job4j.io.netw.bot;

import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static ru.job4j.io.netw.bot.Constants.*;

public class ClientCliAppIntegrationTest {
    @Test
    public void whenMainThenAllowsToSendRequests() {
        String request = String.join(NL, "test", EXIT_WORD, "");
        System.setIn(new ByteArrayInputStream(request.getBytes()));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        List<String> received = new ArrayList<>();
        startServer(received, "test ok", "bye ok");
        ClientCliApp.main(null);
        List<String> expectedRequests = List.of("test", EXIT_WORD);
        String expectedResponses = String.join(System.lineSeparator(), "test ok", "bye ok", "");
        assertThat(received, is(expectedRequests));
        assertThat(buffer.toString(), is(expectedResponses));
    }

    private void startServer(List<String> received, String... responses) {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                Socket incoming = serverSocket.accept();
                OutputStream out = incoming.getOutputStream();
                Scanner scanner = new Scanner(incoming.getInputStream());
                for (String response : responses) {
                    received.add(scanner.nextLine());
                    out.write((response + END).getBytes());
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
