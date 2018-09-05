package ru.job4j.professions;

public class Profession {
    private final String name;
    private final String profession;

    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
