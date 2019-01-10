package ru.job4j.collections.stats;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static ru.job4j.collections.stats.Analyzer.*;

public class AnalyzerTest {

    @Test
    public void whenDiffSameElementsThenReturnsNoChanges() {
        Info info = new Analyzer().diff(List.of(
                new User(1, "Bob"),
                new User(2, "Alice")
        ), List.of(
                new User(2, "Alice"),
                new User(1, "Bob")
        ));
        assertInfo(info, 0, 0, 0);
    }

    @Test
    public void whenDiffOneAddedElementThenReturnsAddedOne() {
        Info info = new Analyzer().diff(List.of(
                new User(1, "Bob")
        ), List.of(
                new User(2, "Alice"),
                new User(1, "Bob")
        ));
        assertInfo(info, 1, 0, 0);
    }

    @Test
    public void whenDiffOneDeletedElementThenReturnsDeletedOne() {
        Info info = new Analyzer().diff(List.of(
                new User(1, "Bob"),
                new User(2, "Alice")
        ), List.of(
                new User(2, "Alice")
        ));
        assertInfo(info, 0, 0, 1);
    }

    @Test
    public void whenDiffChangedElementThenReturnsChangedOne() {
        Info info = new Analyzer().diff(List.of(
                new User(1, "Bob"),
                new User(2, "Alice")
        ), List.of(
                new User(2, "Alice"),
                new User(1, "Bob Martin")
        ));
        assertInfo(info, 0, 1, 0);
    }

    private void assertInfo(Info info, int added, int changed, int deleted) {
        assertThat(info.added, is(added));
        assertThat(info.changed, is(changed));
        assertThat(info.deleted, is(deleted));
    }
}
