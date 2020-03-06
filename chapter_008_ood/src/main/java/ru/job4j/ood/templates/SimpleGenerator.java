package ru.job4j.ood.templates;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generator of template text.
 * It uses regular expressions.
 */
public class SimpleGenerator implements Template {
    private static final String KEY_REGEX = "\\$\\{(\\w+)}";
    private static final Pattern PATTERN = Pattern.compile(KEY_REGEX);

    /**
     * Generates text using the specified template and key-value pairs.
     * @param template template of the text where each value substitutes pattern <code>${key}</code>
     * @param map map of key-value pairs
     * @return generated text
     */
    @Override
    public String generate(String template, Map<String, String> map) {
        Matcher matcher = PATTERN.matcher(template);
        StringBuilder result = new StringBuilder(template);
        int offset = 0;
        boolean containsKey = false;
        while (matcher.find()) {
            containsKey = true;
            int start = matcher.start() + offset;
            int end = matcher.end() + offset;
            String placeHolder = matcher.group();
            String key = matcher.group(1);
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException("The template contains key that is not in the map: " + key);
            }
            String value = map.get(key);
            value = value != null ? value : "null";
            result.replace(start, end, value);
            offset += value.length() - placeHolder.length();
        }
        if (!containsKey) {
            throw new IllegalArgumentException("The template text does not contains any keys: " + template);
        }
        return result.toString();
    }
}
