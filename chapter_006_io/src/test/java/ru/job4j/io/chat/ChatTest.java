package ru.job4j.io.chat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class ChatTest {
    private final Listener output = new Listener();
    private final Listener logger = new Listener();

    @Test
    public void whenTypeMessageThenResponseIsReceivedAndConversationIsLogged() {
        Talker human = new Talker(List.of("1-Hello", "quit"));
        Talker bot = new Talker(List.of("2-Goodbye"));
        new Chat(human, bot, output, logger).start();
        var botResponse = List.of("2-Goodbye");
        var conversation = List.of("1-Hello", "2-Goodbye", "quit");
        assertThat(output.getPhrases(), is(botResponse));
        assertThat(logger.getPhrases(), is(conversation));
    }

    @Test
    public void whenStoppedThenNoResponse() {
        Talker human = new Talker(List.of("1-Hello", "stop", "1-Quiet", "continue", "1-Loud", "quit"));
        Talker bot = new Talker(List.of("2-Hi", "2-Bye"));
        new Chat(human, bot, output, logger).start();
        var botResponse = List.of("2-Hi", "2-Bye");
        var conversation = List.of("1-Hello", "2-Hi", "stop", "1-Quiet", "continue", "1-Loud", "2-Bye", "quit");
        assertThat(output.getPhrases(), is(botResponse));
        assertThat(logger.getPhrases(), is(conversation));
    }

    private static class Talker implements Supplier<String> {
        private final List<String> phrases;
        private int index;

        public Talker(List<String> phrases) {
            this.phrases = phrases;
        }

        @Override
        public String get() {
            return phrases.get(index++);
        }
    }

    private static class Listener implements Consumer<String> {
        private final List<String> phrases = new ArrayList<>();

        @Override
        public void accept(String s) {
            phrases.add(s);
        }

        public List<String> getPhrases() {
            return phrases;
        }
    }
}
