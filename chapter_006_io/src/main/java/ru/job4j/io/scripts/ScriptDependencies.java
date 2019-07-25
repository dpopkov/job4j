package ru.job4j.io.scripts;

import java.util.*;

/**
 * Задан список скриптов с указанием их зависимосей.
 * 1 - [2, 3], 2 - [4], 3 - [4, 5], 4 - [], 5 - []
 * Необходим написать метод, который возвращает список всех скриптов,
 * которые нужны для загрузки входящего скрипта.
 * Например, чтобы выполнить скрипт 1. нужно выполнить скрипт (2, 3),
 * которые в свою очередь зависят от 4 и 5 скрипта.
 */
public class ScriptDependencies {
    /**
     * Возвращает список id всех скриптов, которые нужны для загрузки заданного скрипта
     * @param dependencies зависимости скриптов
     * @param scriptId id заданного скрипта
     * @return список id скриптов или пустой список если нет зависимосей
     */
    List<Integer> load(Map<Integer, List<Integer>> dependencies, Integer scriptId) {
        List<Integer> idList = dependencies.get(scriptId);
        if (idList == null) {
            return List.of();
        }
        LinkedHashSet<Integer> idSet = new LinkedHashSet<>();
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.add(idList);
        while (!queue.isEmpty()) {
            for (Integer id : queue.remove()) {
                idSet.add(id);
                idList = dependencies.get(id);
                if (idList != null && !idList.isEmpty()) {
                    queue.add(idList);
                }
            }
        }
        return new ArrayList<>(idSet);
    }
}
