package com.tugrulaslan;

public class BubbleSort {
    /**
     * The method sorts the given array of index in the Bubble sort fashion
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        final int arrLen = unsortedArray.length;
        for (int i = 0; i < arrLen; i++) {
            final int nestedMax = arrLen - 1 - i;
            for (int j = 0; j < nestedMax; j++) {
                int curr = unsortedArray[j];
                int next = unsortedArray[j + 1];
                if (curr > next) {
                    int temp = curr;
                    unsortedArray[j] = next;
                    unsortedArray[j + 1] = temp;
                }
            }
        }
        return unsortedArray;
    }

    /**
     * The method sorts the given array of index in the Bubble sort optimized fashion
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] optimizedSort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        int i = 0;
        int arrLen = unsortedArray.length;
        boolean swapped = true;
        while (i < arrLen - 1 && swapped) {
            swapped = false;
            for (int j = 1; j < arrLen - i; j++) {
                if (unsortedArray[j - 1] > unsortedArray[j]) {
                    int temp = unsortedArray[j - 1];
                    unsortedArray[j - 1] = unsortedArray[j];
                    unsortedArray[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
            i++;
        }
        return unsortedArray;
    }
}
