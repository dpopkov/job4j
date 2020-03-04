package ru.job4j.ood.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Distributes food items into stores.
 */
public class ControlQuality {
    private static final Logger LOG = LogManager.getLogger(ControlQuality.class);

    /** Stores that receive food items. */
    private final List<Store> stores;

    /** Initializes control instance with the specified list of stores. */
    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    /** Moves food items to destination stores. */
    public void sort(List<Food> foods) {
        for (Food food : foods) {
            add(food);
        }
    }

    /** Moves the specified food item to a receiving store. */
    public void add(Food food) {
        for (Store store : stores) {
            if (store.accepts(food)) {
                LOG.trace("{} goes to {}", food, store);
                store.add(food);
                break;
            }
        }
    }

    /** Redistributes foods in all stores. */
    public void resort() {
        List<Food> redistributedFood = new ArrayList<>();
        for (Store store : stores) {
            redistributedFood.addAll(store.takeAll());
        }
        redistributedFood.forEach(f -> f.setDiscount(null));
        sort(redistributedFood);
    }
}
