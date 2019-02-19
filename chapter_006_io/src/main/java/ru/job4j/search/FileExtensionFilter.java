package ru.job4j.search;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A filter for abstract path names that filters normal files by extension.
 */
public class FileExtensionFilter implements FileFilter {
    /** Set of extensions. */
    private Set<String> extSet;

    /**
     * Constructs the filter using the specified list of extensions.
     * @param extensions list of extensions
     */
    public FileExtensionFilter(List<String> extensions) {
        if (extensions != null) {
            this.extSet = new HashSet<>(extensions);
        }
    }

    /**
     * Tests whether or not the specified abstract pathname represents a file
     * with extension equal to an extension from the list of this filter.
     * @param file The abstract pathname to be tested
     * @return <code>true</code> if and only if <code>pathname</code>
     * should be accepted
     */
    @Override
    public boolean accept(File file) {
        return extSet == null || extSet.contains(getExtension(file.getName()));
    }

    /**
     * Tests whether or not the specified path represents a file
     * with extension equal to an extension from the list of this filter.
     * @param path The path to be tested
     * @return <code>true</code> if and only if <code>path</code>
     * should be accepted
     */
    public boolean accept(Path path) {
        return extSet == null || extSet.contains(getExtension(path.getFileName().toString()));
    }

    /**
     * Tests whether or not the specified path represents a file
     * with extension not equal to any extension from the list of this filter.
     * @param path The path to be tested
     * @return <code>true</code> if and only if <code>path</code>
     * should be rejected
     */
    public boolean reject(Path path) {
        return !accept(path);
    }

    /**
     * Gets extension of the specified file name.
     * @param filename name of the file
     * @return extension of the file
     */
    private static String getExtension(String filename) {
        String ext = "";
        int pos = filename.lastIndexOf('.');
        if (pos >= 0) {
            ext = filename.substring(pos + 1);
        }
        return ext;
    }
}
