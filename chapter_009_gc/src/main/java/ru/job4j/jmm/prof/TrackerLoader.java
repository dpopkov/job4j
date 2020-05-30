package ru.job4j.jmm.prof;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;

public class TrackerLoader {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerLoader.class);

    private final ITracker tracker;
    private final int initialNumItems;
    private final int numReplacements ;
    private final long delayInMs;

    public TrackerLoader(ITracker tracker, int initialNumItems, int numReplacements, long delayInMs) {
        this.tracker = tracker;
        this.initialNumItems = initialNumItems;
        this.numReplacements = numReplacements;
        this.delayInMs = delayInMs;
    }

    public void addItems() {
        LOG.info("Starting adding {} items", initialNumItems);
        for (int i = 0; i < initialNumItems; i++) {
            Item item = new Item("name-" + i, "description-" + i, System.currentTimeMillis());
            tracker.add(item);
        }
        LOG.info("Number of items added to Tracker: {}", initialNumItems);
    }

    public void replaceItems() throws InterruptedException {
        LOG.info("Starting replacing {} items", numReplacements);
        for (int i = 0; i < numReplacements; i++) {
            List<Item> found = tracker.findByName("name-" + i);
            if (found.size() > 0) {
                Item item = found.get(0);
                Item newItem = new Item("new-name-" + i, "description-" + i, System.currentTimeMillis());
                tracker.replace(item.getId(), newItem);
                if (delayInMs > 0) {
                    Thread.sleep(delayInMs);
                }
            }
        }
        LOG.info("Number of replaced items: {}", numReplacements);
    }

    public static void main(String[] args) {
        int numInitial = 1000;
        long delay = 0L;
        if (args.length > 0) {
            numInitial = Integer.parseInt(args[0]);
        }
        int numReplacements = numInitial;
        if (args.length > 1) {
            numReplacements = Integer.parseInt(args[1]);
            if (numReplacements > numInitial) {
                throw new IllegalArgumentException("Number of replacements greater than number of items");
            }
        }
        if (args.length > 2) {
            delay = Long.parseLong(args[1]);
        }
        ITracker tracker = new Tracker();
        TrackerLoader loader = new TrackerLoader(tracker, numInitial, numReplacements, delay);
        loader.addItems();
        try {
            loader.replaceItems();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
