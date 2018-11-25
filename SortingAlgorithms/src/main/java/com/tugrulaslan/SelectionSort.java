package com.tugrulaslan;

public class SelectionSort {

    /**
     * The method sorts the given array of index in the Selection sort fashion
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        //outer loop
        for (int i = 0; i < unsortedArray.length - 1; i++) {
            //position of the minimum index
            int minIndex = i;

            //inner loop
            for (int j = i + 1; j < unsortedArray.length; j++) {
                //compares minimum value from the previous iteration to the inner loops current iteration value
                if (unsortedArray[minIndex] > unsortedArray[j]) {
                    minIndex = j;
                }
            }
            //if the min index position is not equal to the outer index, then swap
            if (minIndex != i) swap(unsortedArray, i, minIndex);
        }
        return unsortedArray;
    }

    private void swap(int[] unsortedArray, int maxValueIndex, int minValueIndex) {
        //store the max value
        int maxValueTemp = unsortedArray[maxValueIndex];
        //move min value to the max value
        unsortedArray[maxValueIndex] = unsortedArray[minValueIndex];
        //assign max temp value in min value's old position
        unsortedArray[minValueIndex] = maxValueTemp;
    }

}
