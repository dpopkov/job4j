package ru.job4j.io.netw.bot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static ru.job4j.io.netw.bot.Constants.*;

/**
 * Client that allows to send questions and receive responses using an open socket.
 */
public class Client {
    /** Client socket that is used for getting I/O streams. */
    private final Socket socket;
    /** Supplier of requests. */
    private final Supplier<String> questions;
    /** Consumer of responses. */
    private final Consumer<String> responseConsumer;

    /**
     * Creates the client initialized with the specified socket.
     * @param socket open socket
     * @param questions source of questions
     * @param responseConsumer destination for received responses
     */
    public Client(Socket socket, Supplier<String> questions, Consumer<String> responseConsumer) {
        this.socket = socket;
        this.questions = questions;
        this.responseConsumer = responseConsumer;
    }

    /**
     * Starts the sequence of sending and receiving messages.
     * The sequence ends when the client sends {@link Constants#EXIT_WORD} message.
     * @throws IOException if I/O error occurs when creating I/O streams for the socket or the socket is not connected
     */
    public void start() throws IOException {
        try (Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {
            boolean speaking = true;
            StringBuilder builder = new StringBuilder();
            while (speaking) {
                String question = questions.get();
                out.print(question);
                out.print(Constants.NL);
                out.flush();
                builder.setLength(0);
                String line;
                boolean inResponse = true;
                while (inResponse && in.hasNextLine()) {
                    line = in.nextLine();
                    if (line.isEmpty()) {
                        inResponse = false;
                    } else {
                        if (builder.length() > 0) {
                            builder.append(NL);
                        }
                        builder.append(line);
                    }
                }
                responseConsumer.accept(builder.toString());
                if (question.equalsIgnoreCase(EXIT_WORD)) {
                    speaking = false;
                }
            }
        }
    }
}
