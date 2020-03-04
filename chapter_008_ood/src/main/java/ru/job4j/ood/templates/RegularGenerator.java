package ru.job4j.ood.templates;

import java.util.Map;

/**
 * Generator of template text.
 * It uses only classes {@code String} and {@code StringBuilder}.
 */
public class RegularGenerator implements Template {
    private static final String DEFAULT_START_SYMBOL = "${";
    private static final String DEFAULT_END_SYMBOL = "}";

    private final String startSymbol;
    private final String endSymbol;
    private final int startLength;
    private final int endLength;

    /**
     * Creates generator with default key boundary symbols for <code>${key}</code> pattern.
     */
    public RegularGenerator() {
        this(DEFAULT_START_SYMBOL, DEFAULT_END_SYMBOL);
    }

    /**
     * Creates generator and initialises it with the specified boundary symbols.
     * @param startSymbol start of key pattern
     * @param endSymbol end of key pattern
     */
    public RegularGenerator(String startSymbol, String endSymbol) {
        this.startSymbol = startSymbol;
        this.endSymbol = endSymbol;
        startLength = startSymbol.length();
        endLength = endSymbol.length();
    }

    /**
     * Generates text using the specified template and key-value pairs.
     * @param template template of the text where each value substitutes pattern <code>${key}</code>
     * @param map map of key-value pairs
     * @return generated text
     */
    @Override
    public String generate(String template, Map<String, String> map) {
        StringBuilder result = new StringBuilder(template);
        int fromIndex = 0;
        boolean containsKey = false;
        while (true) {
            int start = result.indexOf(startSymbol, fromIndex);
            if (start == -1) {
                break;
            }
            int end = result.indexOf(endSymbol, start + startLength) + endLength;
            if (end == -1) {
                break;
            }
            containsKey = true;
            String pattern = result.substring(start, end);
            String key = result.substring(start + startLength, end - endLength);
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException("The template contains key that is not in the map: " + key);
            }
            String value = map.get(key);
            value = value != null ? value : "null";
            result.replace(start, end, value);
            fromIndex = end + endLength - (pattern.length() - value.length());
        }
        if (!containsKey) {
            throw new IllegalArgumentException("The template text does not contains any keys: " + template);
        }
        return result.toString();
    }
}
