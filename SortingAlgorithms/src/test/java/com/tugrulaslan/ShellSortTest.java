package com.tugrulaslan;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ShellSortTest {
    private static ShellSort shellSort;

    @Before
    public void init() {
        shellSort = new ShellSort();
    }

    @Test
    public void shellSortNullTest() {
        final int[] actual = null;
        final int[] expected = null;
        assertArrayEquals("Sort method did not return a null-value-array", expected, shellSort.sort(actual));
    }

    @Test
    public void shellSortZeroValueTest() {
        final int[] actual = {};
        final int[] expected = {};
        assertArrayEquals("Sort method did not return a zero-value-array", expected, shellSort.sort(actual));
    }

    @Test
    public void shellSortOneValueTest() {
        final int[] actual = {1};
        final int[] expected = {1};
        assertArrayEquals("Sort method did not return a one-value-array", expected, shellSort.sort(actual));
    }

    @Test
    public void shellSortProperTest() {
        final int[] actual = {5, 2, 6, 3, 1, 9};
        final int[] expected = {1, 2, 3, 5, 6, 9};
        assertArrayEquals("Sort method did not return a one-value-array", expected, shellSort.sort(actual));
    }
}
