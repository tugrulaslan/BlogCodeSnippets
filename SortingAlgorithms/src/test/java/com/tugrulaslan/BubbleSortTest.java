package com.tugrulaslan;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest {
    private static BubbleSort bubbleSort;

    @Before
    public void init() {
        bubbleSort = new BubbleSort();
    }

    @Test
    public void bubbleSortNullTest() {
        final int[] actual = null;
        final int[] expected = null;
        assertArrayEquals("Sort method did not return a null-value-array", expected, bubbleSort.sort(actual));
        assertArrayEquals("Sort method did not return a null-value-array", expected, bubbleSort.optimizedSort(actual));
    }

    @Test
    public void bubbleSortZeroValueTest() {
        final int[] actual = {};
        final int[] expected = {};
        assertArrayEquals("Sort method did not return a zero-value-array", expected, bubbleSort.sort(actual));
        assertArrayEquals("Sort method did not return a zero-value-array", expected, bubbleSort.optimizedSort(actual));
    }

    @Test
    public void bubbleSortOneValueTest() {
        final int[] actual = {1};
        final int[] expected = {1};
        assertArrayEquals("Sort method did not return a one-value-array", expected, bubbleSort.sort(actual));
        assertArrayEquals("Sort method did not return a one-value-array", expected, bubbleSort.optimizedSort(actual));
    }

    @Test
    public void bubbleSortProperTest() {
        final int[] actual = {5, 2, 6, 3, 1, 9};
        final int[] actual2 = {5, 2, 6, 3, 1, 9};
        final int[] expected = {1, 2, 3, 5, 6, 9};
        assertArrayEquals("Sort method did not return a one-value-array", expected, bubbleSort.sort(actual));
        assertArrayEquals("Sort method did not return a one-value-array", expected, bubbleSort.optimizedSort(actual2));
    }
}
