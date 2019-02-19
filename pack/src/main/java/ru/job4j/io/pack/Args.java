package ru.job4j.io.pack;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser of command line arguments.
 */
public class Args {
    /** Path to the source directory. */
    private String dir;
    /** List of file extensions that should be excluded. */
    private List<String> extList;
    /** Path to the output file. */
    private String out;

    /** Constructs instance using array of command line arguments. */
    public Args(String[] args) {
        parse(args);
    }

    /**
     * Parses command line arguments.
     * @param args array of arguments
     */
    private void parse(String[] args) {
        extList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String token = args[i];
            if ("-d".equals(token)) {
                dir = args[++i];
            } else if ("-e".equals(token)) {
                int j = i + 1;
                while (j < args.length) {
                    token = args[j];
                    if (token.startsWith("-")) {
                        break;
                    } else {
                        extList.add(token);
                        j++;
                    }
                }
                i = j - 1;
            } else if ("-o".equals(token)) {
                out = args[++i];
            }
        }
        if (dir == null) {
            throw new IllegalArgumentException("args should contain '-d' 'directory' arguments");
        }
        if (out == null) {
            out = dir + ".zip";
        }
    }

    /**
     * @return path to the source directory
     */
    public String directory() {
        return dir;
    }

    /**
     * @return list of file extensions that should be excluded
     */
    public List<String> exclude() {
        return extList;
    }

    /**
     * @return path to the output file
     */
    public String output() {
        return out;
    }
}
