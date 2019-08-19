package ru.job4j.sqltoxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    private int field;

    /** Default constructor is used by XML binding. */
    @SuppressWarnings("unused")
    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Entry{field=" + field + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return field == ((Entry) obj).field;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(field);
    }
}
