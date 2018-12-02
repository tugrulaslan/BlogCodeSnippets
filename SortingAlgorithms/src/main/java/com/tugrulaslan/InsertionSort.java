package com.tugrulaslan;

public class InsertionSort {

    /**
     * The method sorts the given array of index in the Insertion sort fashion using recursive
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] recursiveSort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        recursiveSort(unsortedArray, unsortedArray.length);
        return unsortedArray;
    }

    /**
     * The private method makes a recursive calls to itself until the @param i is 2 then the operation begins
     * the comparison begins from the right to the left  as the first element is skipped until the algorithm reaches to the last element.
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @param i             the position of the element that will be compared
     * @return int[] a sorted array
     */
    private void recursiveSort(int[] unsortedArray, int i) {
        if (i <= 1) return;
        recursiveSort(unsortedArray, i - 1);
        int key = unsortedArray[i - 1];
        int j = i - 2;
        while (j >= 0 && unsortedArray[j] > key) {
            unsortedArray[j + 1] = unsortedArray[j];
            j--;
        }
        unsortedArray[j + 1] = key;
    }

    /**
     * The method sorts the given array of index in the Insertion sort fashion imperatively
     * the comparison begins from the right to the left  as the first element is skipped until the algorithm reaches to the last element.
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] imperativeSort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        int arrayLen = unsortedArray.length;
        for (int i = 1; i < arrayLen; i++) {
            int key = unsortedArray[i];
            int j = i - 1;
            while (j >= 0 && unsortedArray[j] > key) {
                unsortedArray[j + 1] = unsortedArray[j];
                j--;
            }
            unsortedArray[j + 1] = key;
        }
        return unsortedArray;
    }
}
