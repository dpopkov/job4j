package ru.job4j.io.chat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main class that starts the console chat.
 */
public class ConsoleChatApp {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java ConsoleChatApp inputFile logFile");
            return;
        }
        Path input = Paths.get(args[0]);
        Path log = Paths.get(args[1]);
        ConsoleChat chat = new ConsoleChat(input, log);
        try {
            chat.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
