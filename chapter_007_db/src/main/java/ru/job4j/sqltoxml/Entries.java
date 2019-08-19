package ru.job4j.sqltoxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    @XmlElement(name = "entry")
    private List<Entry> entries;

    /** Default constructor is used by XML binding. */
    @SuppressWarnings("unused")
    public Entries() {
    }

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }
}
