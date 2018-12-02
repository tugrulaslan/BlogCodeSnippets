package com.tugrulaslan;

import java.util.Arrays;

public class MergeSort extends ComparisonSort {
    /**
     * The method sorts the given array of index in the Merge sort fashion
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        sort(unsortedArray, 0, unsortedArray.length - 1);
        return unsortedArray;
    }

    /**
     * The private method calls recursively until the right pointer meets the left pointer,
     * it splits until the array is broken to single units then compares and merges back
     *
     * @param unsortedArray an unsorted subset of array
     * @param leftPointer   the left side of the the array, it may also be known as the bottom of the tree
     * @param rightPointer  the right side of the the array, it may also be known as the top of the tree
     */
    private void sort(int[] unsortedArray, int leftPointer, int rightPointer) {
        if (leftPointer < rightPointer) {
            //locate the middle element
            int middleElementPointer = (leftPointer + rightPointer) / 2;
            //sort the left side of the subset from the left pointer and to the middle element
            sort(unsortedArray, leftPointer, middleElementPointer);
            //sort the left side of the subset from the middle element plus to the left pointer
            sort(unsortedArray, middleElementPointer + 1, rightPointer);
            //merge the divides elements back together
            merge(unsortedArray, leftPointer, middleElementPointer, rightPointer);
        }
    }

    /**
     * The private method merges broken pieces back to themselves
     * 1. creates two temporary arrays for the left and right subsets,
     * 2. fills the two temporary arrays with the data
     * 3. merges the data back into the original array
     *
     * @param unsortedArray an unsorted subset of array
     * @param leftPointer   the position of the left element, also know as the bottom of the tree
     * @param middleElement the position of element in the center
     * @param middleElement the position of the right element, also know as the top of the tree
     */
    private void merge(int[] unsortedArray, int leftPointer, int middleElement, int rightPointer) {
        int rightArrayElementCapacity = rightPointer - middleElement;
        int leftArrayElementCapacity = middleElement - leftPointer + 1;

        // creates two temporary arrays for the left and right subsets
        int rightArray[] = new int[rightArrayElementCapacity];
        int leftArray[] = new int[leftArrayElementCapacity];

        //fills the two temporary arrays with the data
        for (int i = 0; i < leftArrayElementCapacity; ++i)
            leftArray[i] = unsortedArray[leftPointer + i];
        for (int j = 0; j < rightArrayElementCapacity; ++j)
            rightArray[j] = unsortedArray[middleElement + 1 + j];


        int i = 0, j = 0;

        // merges the data back into the original array
        int k = leftPointer;
        while (i < leftArrayElementCapacity && j < rightArrayElementCapacity) {
            if (leftArray[i] <= rightArray[j]) {
                unsortedArray[k] = leftArray[i];
                i++;
            } else {
                unsortedArray[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // copy the rest of the elements to the left array
        while (i < leftArrayElementCapacity) {
            unsortedArray[k] = leftArray[i];
            i++;
            k++;
        }

        // copy the rest of the elements to the right array
        while (j < rightArrayElementCapacity) {
            unsortedArray[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
