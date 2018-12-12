package com.tugrulaslan.datastructures.queue;

public class Queue {
    private Node head;
    private Node tail;

    public boolean isEmpty() {
        return head == null;
    }

    public int peek() {
        if (isEmpty()) throw new NullPointerException("Queue is null");
        return head.getData();
    }

    /**
     * Public method enqueues(inserts) the given node to the queue
     * <p>
     *
     * @param node node that will be enqueued
     */
    public void enqueue(Node node) {

    }

    /**
     * Public method dequeues(removes) the given node to the queue
     * <p>
     *
     * @param node node that will be enqueued
     * @return int integer value of the dequeued element from the queue
     */
    public int dequeue(Node node) {
        return 0;
    }

    public void add(Node node) {
        if (tail != null) {
            tail.next = node;
        }
    }
}
