package ru.job4j.collections.questions;

import java.util.Random;
import java.util.Scanner;

/**
 * Класс проверяет время работы метода {@code StringChars::haveSameCharacters}.
 *
 * Получен результат:
 * 1000    95   ms
 * 2000    170  ms
 * 4000    286  ms
 * 8000    492  ms
 */
public class StringCharsTimeEstimate {
    public static void main(String[] args) {
        int length;
        if (args.length != 1) {
            Scanner in = new Scanner(System.in);
            System.out.print("Need word's length: ");
            length = in.nextInt();
        } else {
            length = Integer.parseInt(args[0]);
        }
        final int numTests = 1000;
        long total = 0, start;
        StringChars sc = new StringChars();
        for (int i = 0; i < numTests; i++) {
            String s1 = make(length);
            @SuppressWarnings("StringOperationCanBeSimplified")
            String s2 = new String(s1);
            start = System.currentTimeMillis();
            sc.haveSameCharacters(s1, s2);
            total += System.currentTimeMillis() - start;
        }
        System.out.printf("Words length %d processed in %d milliseconds%n", length, total);
    }

    private static String make(int length) {
        Random rnd = new Random();
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) (32 + rnd.nextInt(223));
        }
        return new String(bytes);
    }
}
