package ru.job4j.io.chat;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Simulates a conversation between two talkers.
 * When the first talker sends 'stop' then the second talker stops responding.
 * When the first talker sends 'continue' the the second talker resumes responding.
 * When the first talker sends 'quit' then the conversation is over.
 */
public class Chat {
    /** The first talker. */
    private final Supplier<String> talker1;
    /** The second talker. */
    private final Supplier<String> talker2;
    /** Output receiving responses from the second talker. */
    private final Consumer<String> output;
    /** Logger that saves the whole conversation. */
    private final Consumer<String> logger;

    /**
     * Constructs the chat with the specified talkers, output and logger.
     * @param talker1 the first talker that starts and finishes the conversation
     * @param talker2 the second talker that gives responses
     * @param output output receiving responses from the second talker
     * @param logger logger that saves the whole conversation
     */
    public Chat(Supplier<String> talker1, Supplier<String> talker2, Consumer<String> output, Consumer<String> logger) {
        this.talker1 = talker1;
        this.talker2 = talker2;
        this.output = output;
        this.logger = logger;
    }

    /**
     * Starts the chat.
     * The second talker is suspended when the first talker sends 'stop',
     * and resumed when the first sends 'continue'.
     * The chat is over when the first talker sends 'quit'.
     */
    public void start() {
        boolean running = true;
        boolean responding = true;
        while (running) {
            String phrase = talker1.get();
            logger.accept(phrase);
            if ("stop".equals(phrase)) {
                responding = false;
            } else if ("continue".equals(phrase)) {
                responding = true;
            } else if ("quit".equals(phrase)) {
                running = false;
            } else if (responding) {
                String response = talker2.get();
                output.accept(response);
                logger.accept(response);
            }
        }
    }
}
