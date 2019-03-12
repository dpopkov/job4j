package ru.job4j.io.netw.bot;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static ru.job4j.io.netw.bot.Constants.*;

/**
 * Allows to enter requests in command line interface (CLI) and receive responses from a server.
 */
public class ClientCliApp {
    /**
     * Main method starting the application.
     * @param args args[0] - port number
     */
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        try {
            Socket socket = new Socket("localhost", port);
            Scanner in = new Scanner(System.in);
            Client client = new Client(socket, in::nextLine, System.out::println);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
