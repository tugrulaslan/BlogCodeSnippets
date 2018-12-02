package com.tugrulaslan;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class InsertionSortTest {
    private static InsertionSort insertionSort;

    @Before
    public void init() {
        insertionSort = new InsertionSort();
    }

    @Test
    public void insertionSortNullTest() {
        final int[] actual = null;
        final int[] expected = null;
        assertArrayEquals("Sort method did not return a null-value-array", expected, insertionSort.recursiveSort(actual));
        assertArrayEquals("Sort method did not return a null-value-array", expected, insertionSort.imperativeSort(actual));
    }

    @Test
    public void insertionSortZeroValueTest() {
        final int[] actual = {};
        final int[] expected = {};
        assertArrayEquals("Sort method did not return a zero-value-array", expected, insertionSort.recursiveSort(actual));
        assertArrayEquals("Sort method did not return a zero-value-array", expected, insertionSort.imperativeSort(actual));
    }

    @Test
    public void insertionSortOneValueTest() {
        final int[] actual = {1};
        final int[] expected = {1};
        assertArrayEquals("Sort method did not return a one-value-array", expected, insertionSort.recursiveSort(actual));
        assertArrayEquals("Sort method did not return a one-value-array", expected, insertionSort.imperativeSort(actual));
    }

    @Test
    public void insertionSortProperTest() {
        final int[] actual = {5, 2, 6, 3, 1, 9};
        final int[] actual2 = {5, 2, 6, 3, 1, 9};
        final int[] expected = {1, 2, 3, 5, 6, 9};
        assertArrayEquals("Sort method did not return a one-value-array", expected, insertionSort.recursiveSort(actual));
        assertArrayEquals("Sort method did not return a one-value-array", expected, insertionSort.imperativeSort(actual2));
    }
}
