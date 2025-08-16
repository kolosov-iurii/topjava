package ru.javawebinar.topjava;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Factory for creating test matchers.
 * <p>
 * Comparing actual and expected objects via AssertJ
 */
public class MatcherFactory {
    public static <T> Matcher<T> usingIgnoringFieldsComparator(String... fieldsToIgnore) {
        return new Matcher<>(fieldsToIgnore);
    }

    public static class Matcher<T> {
        private final BiConsumer<T, T> assertion;
        private final BiConsumer<Iterable<T>, Iterable<T>> iterableAssertion;

        private Matcher(String... fieldsToIgnore) {
            this.assertion = assertion;
            this.iterableAssertion = iterableAssertion;
        }

        public void assertMatch(T actual, T expected) {
            assertion.accept(actual, expected);
        }

        @SafeVarargs
        public final void assertMatch(Iterable<T> actual, T... expected) {
            assertMatch(actual, Arrays.asList(expected));
        }

        public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
            assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
        }
    }
}
