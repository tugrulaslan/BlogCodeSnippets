package com.tugrulaslan.datastructures.queue;

import java.util.NoSuchElementException;

public class Queue {
    private Node head;
    private Node tail;
    private final static NoSuchElementException NO_SUCH_ELEMENT_EXCEPTION = new NoSuchElementException("Queue has no elements");

    /**
     * Public method checks whether the queue is empty
     * <p>
     *
     * @return boolean value of the queue occupation status
     */
    public boolean isEmpty() {
        return tail == null;
    }

    /**
     * Public method returns the first node in the queue
     * <p>
     *
     * @throws NoSuchElementException if the queue is empty
     */
    public Node peek() {
        if (isEmpty()) throw NO_SUCH_ELEMENT_EXCEPTION;
        return head;
    }

    /**
     * Public method enqueues(inserts) the given node to the queue
     * <p>
     *
     * @param node node that will be enqueued
     */
    public void enqueue(Node node) {
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
    }

    /**
     * Public method dequeues(removes) the given node to the queue
     * <p>
     *
     * @return int integer value of the dequeued element from the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Node dequeue() {
        if (isEmpty()) throw NO_SUCH_ELEMENT_EXCEPTION;

        Node dequeuedNode = head;
        head = head.next;
        if (head == null)
            tail = null;
        return dequeuedNode;
    }

}
