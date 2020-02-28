package ru.job4j.ood.menu;

import java.util.function.Consumer;

/**
 * Renders appearance of nested items to any string consumer.
 */
public class ToStringRenderer implements Renderer {
    /** Symbol used for indentation of nested items. */
    private final String indentationSymbol;
    /** Destination of rendering. */
    private final Consumer<String> out;
    /** Suffix appended to the end of string representation of nested items. */
    private final String suffix;

    /**
     * Constructs the renderer using specified indentation symbol, string consumer.
     * System's line separator will be used as suffix
     * */
    public ToStringRenderer(String indentationSymbol, Consumer<String> out) {
        this(indentationSymbol, out, System.lineSeparator());
    }

    /** Constructs the renderer using specified indentation symbol, string consumer, and suffix. */
    public ToStringRenderer(String indentationSymbol, Consumer<String> out, String suffix) {
        this.indentationSymbol = indentationSymbol;
        this.out = out;
        this.suffix = suffix;
    }

    /** Renders appearance of the specified item. */
    @Override
    public void render(NestedItem item) {
        out.accept(indentationSymbol.repeat(item.getLevel())
                + item.name() + suffix);
    }
}
