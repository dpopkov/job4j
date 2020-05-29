package ru.job4j.jmm.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for memory measurements.
 * It should take 24 bytes in memory.
 */
class User {
    private static final Logger LOG = LoggerFactory.getLogger(User.class);

    private final long id;

    public User(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{id='" + id + '\'' + '}';
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        LOG.trace("finalizing User #" + id);
    }
}
