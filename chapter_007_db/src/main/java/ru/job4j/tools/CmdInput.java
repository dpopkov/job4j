package ru.job4j.tools;

import java.util.Scanner;

public class CmdInput {
    private final Scanner scanner = new Scanner(System.in);

    private CmdInput() {}

    public Scanner getScanner() {
        return scanner;
    }

    private static final CmdInput instance = new CmdInput();

    public static long nextLong(String prompt) {
        System.out.print(prompt);
        return instance.getScanner().nextLong();
    }

    public static int nextInt(String prompt) {
        System.out.print(prompt);
        return instance.getScanner().nextInt();
    }

    public static String next(String prompt) {
        System.out.print(prompt);
        return instance.getScanner().next();
    }

    public static String nextLine(String prompt) {
        System.out.print(prompt);
        return instance.getScanner().nextLine();
    }
}
