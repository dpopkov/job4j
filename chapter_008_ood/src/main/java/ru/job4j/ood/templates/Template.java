package ru.job4j.ood.templates;

import java.util.Map;

/**
 * Generator of template text.
 */
public interface Template {
    /**
     * Generates text using the specified template and key-value pairs.
     * @param template template of the text where each value substitutes pattern <code>${key}</code>
     * @param map map of key-value pairs
     * @return generated text
     */
    String generate(String template, Map<String, String> map);
}
