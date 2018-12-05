package com.tugrulaslan;

public class ShellSort extends ComparisonSort {
    /**
     * The method sorts the given array of index in the Shell sort fashion
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        int arrLen = unsortedArray.length;
        for (int gap = arrLen / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arrLen; i += 1) {
                int temp = unsortedArray[i];
                int j;
                for (j = i; j >= gap && unsortedArray[j - gap] > temp; j -= gap) {
                    unsortedArray[j] = unsortedArray[j - gap];
                }
                unsortedArray[j] = temp;
            }
        }
        return unsortedArray;
    }
}
