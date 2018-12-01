package com.tugrulaslan;

import java.util.Arrays;

public class QuickSort extends BaseSort {

    /**
     * The method sorts the given array of index in the Quick sort fashion
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
     * then it swaps and partitions the arrays left and right subsets.
     *
     * @param unsortedArray an unsorted subset of array
     * @return int[] a partitioned, sorted subset of the designated array
     */
    private void sort(int[] unsortedArray, int leftPointer, int rightPointer) {
        if (leftPointer < rightPointer + 1) {
            int partitionPoint = partition(unsortedArray, leftPointer, rightPointer);
            sort(unsortedArray, leftPointer, partitionPoint - 1);
            sort(unsortedArray, partitionPoint + 1, rightPointer);
        }
    }

    /**
     * The private method that partitions the given array
     * 1. Allocates a pivot point which is the right pointer
     * 2. Swaps the left
     *
     * @param unsortedArray an unsorted subset of array
     * @return int[] a partitioned, sorted subset of the designated array
     */
    private int partition(int[] unsortedArray, int leftPointer, int rightPointer) {
        int pivot = unsortedArray[rightPointer];
        int smallerElementIndex = (leftPointer - 1); // index of smaller element
        for (int i = leftPointer; i < rightPointer; i++) {
            // If current element is smaller than or
            // equal to pivot
            if (unsortedArray[i] <= pivot) {
                smallerElementIndex++;
                swap(unsortedArray, smallerElementIndex, i);
            }
        }
        swap(unsortedArray, smallerElementIndex + 1, rightPointer);
        return smallerElementIndex + 1;
    }

}