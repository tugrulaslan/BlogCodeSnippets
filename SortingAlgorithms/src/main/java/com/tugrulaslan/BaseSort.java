package com.tugrulaslan;

public class BaseSort {
    /**
     * Method swaps the two elements in a given element
     * @param unsortedArray array whose values will be swapped
     * @param targetIndex index of the element that will be moved in the array
     * @param destinationIndex index of the element that will be switched to the target element's spot in the array
     */
    protected void swap(int[] unsortedArray, int targetIndex, int destinationIndex) {
        //store the max value
        int maxValueTemp = unsortedArray[targetIndex];
        //move min value to the max value
        unsortedArray[targetIndex] = unsortedArray[destinationIndex];
        //assign max temp value in min value's old position
        unsortedArray[destinationIndex] = maxValueTemp;
    }
}
