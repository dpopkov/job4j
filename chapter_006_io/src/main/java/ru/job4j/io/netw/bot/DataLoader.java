package ru.job4j.io.netw.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Allows to load key-value pairs from a file.
 */
public class DataLoader {
    /** Map that represents key-value pairs of data. */
    private Map<String, String> map;

    /**
     * Loads data from the specified input stream that receives XML data.
     * @param in input stream
     * @throws IOException if error occurs when reading data
     */
    public void loadFromXML(InputStream in) throws IOException {
        Properties data = new Properties();
        data.loadFromXML(in);
        map = new HashMap<>();
        for (String key : data.stringPropertyNames()) {
            map.put(key, data.getProperty(key));
        }
    }

    /**
     * @return data represented by key-value pairs
     */
    public Map<String, String> getMap() {
        return map;
    }
}
