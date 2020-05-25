package ru.job4j.jmm.gc;

/**
 * Принуждает Garbage Collector выполнить сборку мусора если запустить
 * с ограничением максимального размера хипа (опция -Xmx).<br>
 */
public class ForceGarbageCollection {

    public static void main(String[] args) throws Exception {
        System.out.println("start");
        int numObjects = 64_000;
        if (args.length == 1) {
            numObjects = Integer.parseInt(args[0]);
        }
        for (int i = 0; i < numObjects; i++) {
            new User(i);
            Thread.sleep(1L);
        }
        System.out.println("finish");
    }
}
