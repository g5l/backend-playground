package com.example.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class NumbersTest {

    private final Numbers numbers = new Numbers();

    @Test
    @DisplayName("sum() adds correctly for multiple inputs (kills constant-return mutants)")
    void sum_addsCorrectly() {
        assertEquals(5, numbers.sum(2, 3));
        assertEquals(15, numbers.sum(10, 5));
        assertNotEquals(0, numbers.sum(2, 3)); // guard against "always 0" mutant
        assertNotEquals(1, numbers.sum(2, 3)); // guard against "always 1" mutant
    }

    @Test
    @DisplayName("firstN() returns array of expected length and contents (kills empty-array mutant)")
    void firstN_returnsSequence() {
        int[] arr = numbers.firstN(5);
        assertEquals(5, arr.length);
        for (int i = 0; i < 5; i++) {
            assertEquals(i, arr[i]);
        }
    }

    @Test
    @DisplayName("addOne() increments the counter (kills removed-body mutant)")
    void addOne_incrementsCounter() {
        Counter c = new Counter(41);
        numbers.addOne(c);
        assertEquals(42, c.getValue());
    }

    @Test
    @DisplayName("normalize() uppercases & trims non-null (kills always-null mutant)")
    void normalize_uppercasesAndTrims() {
        String result = numbers.normalize("  hello  ");
        assertNotNull(result);
        assertEquals("HELLO", result);
    }

    @Test
    @DisplayName("normalize(null) returns null (baseline behavior not to be overfit)")
    void normalize_allowsNullInput() {
        assertNull(numbers.normalize(null));
    }
}
