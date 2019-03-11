package ru.job4j.io.netw.bot;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.job4j.io.netw.bot.Constants.*;

public class ClientTest {
    @Test
    public void whenStartThenReceivesResponses() throws IOException {
        testClientReception(
                new Questionnaire("Hi, Bob!", "bye"),
                String.join(NL, "Hi, Alice!", "", "Bye!", "", ""),
                List.of("Hi, Alice!", "Bye!")
        );
    }

    @Test
    public void whenStartThenCanReceiveMultilineMessages() throws IOException {
        testClientReception(
                new Questionnaire("Hi, Bob!", "How are you?", "bye"),
                String.join(NL, "Hi, Alice!", "", "I'm fine!", "Thanks!", "", "Bye!", "", ""),
                List.of("Hi, Alice!", String.join(NL, "I'm fine!", "Thanks!"), "Bye!")
        );
    }

    private void testClientReception(Supplier<String> questions, String responses, List<String> expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        when(socket.getOutputStream()).thenReturn(buffer);
        when(socket.getInputStream()).thenReturn(new ByteArrayInputStream(responses.getBytes()));
        List<String> received = new ArrayList<>();
        Client client = new Client(socket, questions, received::add);
        client.start();
        assertThat(received, is(expected));
    }

    private static class Questionnaire implements Supplier<String> {
        private final List<String> responses;
        private int index;

        public Questionnaire(String... responses) {
            this.responses = List.of(responses);
        }

        @Override
        public String get() {
            return responses.get(index++);
        }
    }
}
