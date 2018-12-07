package com.tugrulaslan.datastructures.stack;

import java.util.NoSuchElementException;

public class Stack {
    private Node head;

    /**
     * A methods demonstrates the capacity of the stack
     * <p>
     *
     * @return boolean value of the current Stack status
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Method pushes the given @param node on top of the Stack
     * <p>
     *
     * @param node node that will be pushed on the top
     */
    public void push(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node tempHead = head;
            while (tempHead.next != null) {
                tempHead = tempHead.next;
            }
            tempHead.next = node;
        }
    }

    /**
     * Method pops the element on the top fo the Stack. The element will be removed from the stack
     * <p>
     *
     * @return int integer value of the popped element on top of the Stack
     * @throws NoSuchElementException if the node is empty
     */
    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        if (head.next == null) {
            final int value = head.data;
            head = null;
            return value;
        }
        Node tempHead = head;
        Node previousNode = null;
        while (tempHead.next != null) {
            previousNode = tempHead;
            tempHead = tempHead.next;
        }
        int valueToBeReturned = 0;
        valueToBeReturned = tempHead.data;
        previousNode.next = null;
        return valueToBeReturned;
    }

    /**
     * Method peeks at the element on the top fo the Stack, it does not delete the element
     * <p>
     *
     * @return int integer value of the peek element on top of the Stack
     * @throws NoSuchElementException if the node is empty
     */
    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return head.data;
    }

}
