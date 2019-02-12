package ru.job4j.search;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

/**
 * Contains methods for searching files.
 */
public class Search {
    /**
     * Gets a list of all files with an extension from the specified list of extensions.
     * Uses breadth first search in all subdirectories.
     * @param parent starting directory
     * @param extensions list of extensions
     * @return list of found files
     */
    public List<File> files(String parent, List<String> extensions) {
        FileFilter filter = new FileExtensionFilter(extensions);
        Queue<File> queue = new ArrayDeque<>();
        queue.add(new File(parent));
        List<File> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            File f = queue.remove();
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                if (files != null) {
                    Collections.addAll(queue, files);
                }
            } else if (filter.accept(f)) {
                result.add(f);
            }
        }
        return result;
    }
}
