package ru.job4j.vacparser.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Contains all vacancy information.
 */
public class Vacancy {
    /** Primary key in database. */
    private Integer id;
    /** Name or title of the vacancy. */
    private final String name;
    /** Description of the vacancy. */
    private String description;
    /** Link to the vacancy. */
    private final String link;
    /** Time of the vacancy's creation. */
    private LocalDateTime created;
    /** Time of the vacancy's modification. */
    private final LocalDateTime modified;

    public Vacancy(String name, String link, LocalDateTime modified) {
        this.name = name;
        this.link = link;
        this.modified = modified;
    }

    public Vacancy(String name, String description, String link, LocalDateTime created, LocalDateTime modified) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.created = created;
        this.modified = modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) obj;
        return Objects.equals(id, vacancy.id)
                && Objects.equals(name, vacancy.name)
                && Objects.equals(description, vacancy.description)
                && Objects.equals(link, vacancy.link)
                && Objects.equals(created, vacancy.created)
                && Objects.equals(modified, vacancy.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, link, created, modified);
    }

    @Override
    public String toString() {
        return "Vacancy{id=" + id + ", name='" + name + '\'' + ", description='" + description + '\''
                + ", link='" + link + '\'' + ", created=" + created + ", modified=" + modified + '}';
    }
}
