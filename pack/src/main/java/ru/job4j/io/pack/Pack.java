package ru.job4j.io.pack;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Main class.
 * Usage in command line: java Pack -d directory -e ext1 ext2 -o output.
 * directory - path to the folder we want to archive
 * ext1 ext2 ... - file extensions that we want to exclude from the archive
 * output - path to the archive fle
 */
public class Pack {
    public static void main(String[] args) {
        Args arguments;
        try {
            arguments = new Args(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Error in arguments: " + e.getMessage());
            return;
        }
        try {
            Path result = new FolderArchiver().compress(
                    arguments.directory(), arguments.output(), arguments.exclude());
            System.out.println(result.toAbsolutePath() + " archive created");
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
