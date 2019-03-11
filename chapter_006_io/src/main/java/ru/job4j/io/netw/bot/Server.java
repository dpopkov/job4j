package ru.job4j.io.netw.bot;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static ru.job4j.io.netw.bot.Constants.*;

/**
 * Serves requests using an open socket.
 */
public class Server {
    /** Oracle providing responses. */
    private final Oracle oracle;
    /** Socket used to receive and send data. */
    private final Socket socket;

    /**
     * Constructs the server using the specified open socket and oracle.
     * @param socket open socket
     * @param oracle oracle providing responses
     */
    public Server(Socket socket, Oracle oracle) {
        this.socket = socket;
        this.oracle = oracle;
    }

    /**
     * Starts the sequence of receiving requests and sending responses.
     * The sequence ends when {@link Constants#EXIT_WORD} request is received.
     * @throws IOException if I/O error occurs when creating I/O streams for the socket or the socket is not connected
     */
    public void start() throws IOException {
        try (Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8)) {
            boolean serving = true;
            while (serving) {
                String question = in.nextLine();
                if (question.equalsIgnoreCase(EXIT_WORD)) {
                    serving = false;
                    send(out, "Bye!");
                } else {
                    String answer = oracle.reply(question);
                    send(out, answer);
                }
            }
        }
    }

    /**
     * Sends the specified message to the output writer.
     * @param out output writer
     * @param message a message that may consist of several lines
     */
    private void send(PrintWriter out, String message) {
        out.print(message);
        out.print(END);
        out.flush();
    }
}
