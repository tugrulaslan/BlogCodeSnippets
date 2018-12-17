package com.tugrulaslan;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RadixSortTest {
    private static RadixSort radixSort;

    @Before
    public void init() {
        radixSort = new RadixSort();
    }

    @Test
    public void radixSortSortNullTest() {
        final int[] actual = null;
        final int[] expected = null;
        assertArrayEquals("Sort method did not return a null-value-array", expected, radixSort.sort(actual));
    }

    @Test
    public void radixSortZeroValueTest() {
        final int[] actual = {};
        final int[] expected = {};
        assertArrayEquals("Sort method did not return a zero-value-array", expected, radixSort.sort(actual));
    }

    @Test
    public void radixSortOneValueTest() {
        final int[] actual = {1};
        final int[] expected = {1};
        assertArrayEquals("Sort method did not return a one-value-array", expected, radixSort.sort(actual));
    }

    @Test
    public void radixSortShortProperTest() {
        final int[] actual = {551, 12, 346, 311};
        final int[] expected = {12, 311, 346, 551};
        assertArrayEquals("Sort method did not return a one-value-array", expected, radixSort.sort(actual));
    }

    @Test
    public void radixSortProperTest() {
        final int[] actual = {551, 12, 346, 313, 119, 910, 215, 843, 45};
        final int[] expected = {12, 45, 119, 215, 313, 346, 551, 843, 910};
        assertArrayEquals("Sort method did not return a one-value-array", expected, radixSort.sort(actual));
    }
}
