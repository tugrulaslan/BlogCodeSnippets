package com.tugrulaslan.datastructures.stack;

import java.util.NoSuchElementException;

public class Stack {
    private Node head;

    public boolean isEmpty() {
        return head == null;
    }

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

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");
        return head.data;
    }

}
