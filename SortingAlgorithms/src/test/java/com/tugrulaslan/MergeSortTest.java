package com.tugrulaslan;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {
    private static MergeSort mergeSort;

    @Before
    public void init() {
        mergeSort = new MergeSort();
    }

    @Test
    public void mergeSortSortNullTest() {
        final int[] actual = null;
        final int[] expected = null;
        assertArrayEquals("Sort method did not return a null-value-array", expected, mergeSort.sort(actual));
    }

    @Test
    public void mergeSortZeroValueTest() {
        final int[] actual = {};
        final int[] expected = {};
        assertArrayEquals("Sort method did not return a zero-value-array", expected, mergeSort.sort(actual));
    }

    @Test
    public void mergeSortOneValueTest() {
        final int[] actual = {1};
        final int[] expected = {1};
        assertArrayEquals("Sort method did not return a one-value-array", expected, mergeSort.sort(actual));
    }

    @Test
    public void mergeSortProperTest() {
        final int[] actual = {5, 2, 6, 3, 1, 9};
        final int[] expected = {1, 2, 3, 5, 6, 9};
        assertArrayEquals("Sort method did not return a one-value-array", expected, mergeSort.sort(actual));
    }
}
