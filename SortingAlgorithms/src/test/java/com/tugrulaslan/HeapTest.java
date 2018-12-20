package com.tugrulaslan;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class HeapTest {
    private static HeapSort heapSort;

    @Before
    public void init() {
        heapSort = new HeapSort();
    }

    @Test
    public void heapSortNullTest() {
        final int[] actual = null;
        final int[] expected = null;
        assertArrayEquals("Sort method did not return a null-value-array", expected, heapSort.sort(actual));
    }

    @Test
    public void heapSortZeroValueTest() {
        final int[] actual = {};
        final int[] expected = {};
        assertArrayEquals("Sort method did not return a zero-value-array", expected, heapSort.sort(actual));
    }

    @Test
    public void heapSortOneValueTest() {
        final int[] actual = {1};
        final int[] expected = {1};
        assertArrayEquals("Sort method did not return a one-value-array", expected, heapSort.sort(actual));
    }

    @Test
    public void heapSortProperTest() {
        final int[] actual = {5, 2, 6, 3, 1, 9};
        final int[] expected = {1, 2, 3, 5, 6, 9};
        assertArrayEquals("Sort method did not return a one-value-array", expected, heapSort.sort(actual));
    }
}
