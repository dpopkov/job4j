package ru.job4j.stream9;

import java.util.Objects;

/**
 * Represents a student with full name and score.
 */
public class Student implements Comparable<Student> {
    /** Full name of the student. */
    private final String name;
    /** Score of the student's certificate. */
    private final int score;

    /**
     * Constructs student with the specified full name and score.
     * @param name full name of the student
     * @param score score of the student's certificate
     */
    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return score of the student's certificate
     */
    public int getScore() {
        return score;
    }

    /**
     * Compares this student with other student by score,
     * and then by name.
     * @param other other student
     * @return the value of 0 if the students are equal,
     *          the value less than 0 if this student precedes the other student,
     *          the value greater than 0 if this student succeeds the other student.
     */
    @Override
    public int compareTo(Student other) {
        int rst = Integer.compare(this.score, other.score);
        return rst == 0 ? this.name.compareTo(other.name) : rst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }

    @Override
    public String toString() {
        return "Student{name='" + name
                + "', score=" + score + '}';
    }
}
