package com.tugrulaslan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    /**
     * The method sorts the given array using the Radix sort
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;
        final int BASE_NUMBER = 10;
        List<Integer>[] bucket = new ArrayList[BASE_NUMBER];
        initializeBucket(bucket);
        boolean maxSignificantDigitReached = false;
        int currNumberCurrSignificantDigit;
        int currSignificantDigit = 1;
        while (!maxSignificantDigitReached) {
            maxSignificantDigitReached = true;
            for (Integer currNumber : unsortedArray) {
                currNumberCurrSignificantDigit = currNumber / currSignificantDigit;
                final int currNumberBucketNumber = currNumberCurrSignificantDigit % BASE_NUMBER;
                bucket[currNumberBucketNumber].add(currNumber);
                if (maxSignificantDigitReached == true && currNumberCurrSignificantDigit > 0) {
                    maxSignificantDigitReached = false;
                }
            }
            moveBucketElementsToUnsortedArray(unsortedArray, BASE_NUMBER, bucket);
            currSignificantDigit *= BASE_NUMBER;
        }
        return unsortedArray;
    }

    private void initializeBucket(List<Integer>[] bucket) {
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
    }

    private void moveBucketElementsToUnsortedArray(int[] unsortedArray, int BASE_NUMBER, List<Integer>[] bucket) {
        int unsortedArrIndex = 0;
        for (int i = 0; i < BASE_NUMBER; i++) {
            for (Integer currBucketElement : bucket[i]) {
                unsortedArray[unsortedArrIndex] = currBucketElement;
                unsortedArrIndex++;
            }
            bucket[i].clear();
        }
    }
}
