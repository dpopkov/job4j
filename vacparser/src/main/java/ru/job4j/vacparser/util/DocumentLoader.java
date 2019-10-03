package ru.job4j.vacparser.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

/**
 * Document loader that delays between successive downloads.
 */
public class DocumentLoader implements Function<String, Optional<Document>> {
    private static final Logger LOG = LoggerFactory.getLogger(DocumentLoader.class);
    private static final long DELAY_SPAN = 2000;

    private final Random random = new Random();
    /** Time stamp of last connection. */
    private long connectionStamp = System.currentTimeMillis();
    /** Minimal delay. */
    private final long minDelay;
    /** Maximum delay. */
    private final long maxDelay;

    /**
     * Initializes loader with the specified delay that is distributed between minimal and maximum delay.
     * @param delay delay in milliseconds
     */
    public DocumentLoader(int delay) {
        minDelay = Math.max(delay - DELAY_SPAN / 2, 0);
        maxDelay = delay + DELAY_SPAN / 2;
    }

    /**
     * Performs downloading of a document at the specified URL.
     * @param url URL of the document
     * @return container that may contain the document if the download was success,
     *          empty container otherwise
     */
    @Override
    public Optional<Document> apply(String url) {
        try {
            ensureDelay();
            connectionStamp = System.currentTimeMillis();
            Document document = Jsoup.connect(url).get();
            return Optional.of(document);
        } catch (IOException e) {
            LOG.error("Error loading url: " + url, e);
            return Optional.empty();
        }
    }

    /** Pauses the process of downloading if necessary to ensure delay between connections. */
    private void ensureDelay() {
        long elapsed = System.currentTimeMillis() - connectionStamp;
        if (elapsed < minDelay) {
            long min = minDelay - elapsed;
            long max = maxDelay - elapsed;
            long delay = min + random.nextInt((int) (max - min));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
