package com.tugrulaslan.datastructures.linkedlist;

public abstract class LinkedList {
    private int size;

    /**
     * A method that increases the size of the Linked List.
     */
    public void increaseSize() {
        if (!(size < 0)) size++;
    }

    /**
     * A method that decreases the size of the Linked List.
     * It is to prevent the size to go lower than 0 maintain a positive number
     */
    public void decreaseSize() {
        if (!(size < 0)) size--;
    }

    /**
     * A method returns the current size of the Linked List
     */
    public int size() {
        return size;
    }
}