package ru.job4j.tracker;

/**
 * Represents item for {@link Tracker}.
 */
public class Item {
    /**
     * Unique identifier of item.
     */
    private String id;
    /**
     * Name of item.
     */
    private String name;
    /**
     * Description of item.
     */
    private String desc;
    /**
     * Number of milliseconds which represents time of item's creation.
     */
    private long created;

    /**
     * Constructs {@code Item} instance.
     * @param name name
     * @param description description
     * @param created number of milliseconds representing creation time
     */
    public Item(String name, String description, long created) {
        this.name = name;
        this.desc = description;
        this.created = created;
    }

    /**
     * @return id of the item
     */
    public String getId() {
        return id;
    }

    /**
     * @param id id of the item
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * @param name name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return description of the item
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc description of the item
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return creation time in milliseconds
     */
    public long getCreated() {
        return created;
    }

    /**
     * @param created creation time in milliseconds
     */
    public void setCreated(long created) {
        this.created = created;
    }

    /**
     * @return string representation of {@code Item} instance
     */
    @Override
    public String toString() {
        return "Item{id='" + id + "', name='" + name + "', desc='" + desc + "'}";
    }
}
