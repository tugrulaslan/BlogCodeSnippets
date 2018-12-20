package com.tugrulaslan;

public class HeapSort extends ComparisonSort {

    /**
     * The method sorts the given array using Heap Sort Algorithm
     *
     * @param unsortedArray an unsorted array to be fully sorted by the method
     * @return int[] a sorted array
     */
    public int[] sort(int[] unsortedArray) {
        if (unsortedArray == null || unsortedArray.length == 0 || unsortedArray.length == 1) return unsortedArray;

        int arrSize = unsortedArray.length;

        // Build max heap
        for (int i = arrSize / 2 - 1; i >= 0; i--) {
            heapify(unsortedArray, arrSize, i);
        }

        // Heap sort
        for (int i = arrSize - 1; i >= 0; i--) {
            swap(unsortedArray, 0, i);

            // Heapify root element
            heapify(unsortedArray, i, 0);
        }
        return unsortedArray;
    }

    private void heapify(int arr[], int n, int index) {
        // Find largest among root, left child and right child
        int largest = index;
        int leftNode = 2 * index + 1;
        int rightNode = 2 * index + 2;

        if (leftNode < n && arr[leftNode] > arr[largest])
            largest = leftNode;

        if (rightNode < n && arr[rightNode] > arr[largest])
            largest = rightNode;

        // Swap and continue heapifying if root is not largest
        if (largest != index) {
            swap(arr, index, largest);
            heapify(arr, n, largest);
        }
    }


}
