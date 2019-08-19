package ru.job4j.tools;

import java.util.Scanner;

public class CmdInput {
    private final Scanner scanner = new Scanner(System.in);

    private CmdInput() {
    }

    public Scanner getScanner() {
        return scanner;
    }

    private static final CmdInput INSTANCE = new CmdInput();

    public static long nextLong(String prompt) {
        System.out.print(prompt);
        return INSTANCE.getScanner().nextLong();
    }

    public static int nextInt(String prompt) {
        System.out.print(prompt);
        return INSTANCE.getScanner().nextInt();
    }

    public static String next(String prompt) {
        System.out.print(prompt);
        return INSTANCE.getScanner().next();
    }

    public static String nextLine(String prompt) {
        System.out.print(prompt);
        return INSTANCE.getScanner().nextLine();
    }
}
