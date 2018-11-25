package com.tugrulaslan.datastructures.linkedlist;

public abstract class LinkedList {

    protected Node head;
    protected int size;

    /**
     * Public method checks whether the linked list is empty or not
     * <p>
     *
     * @return Node returns the added Node
     */
    public boolean isEmpty() {
        return head == null;
    }

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

    /**
     * Public method checks whether the linked list containsIterativeSearch the given node
     * The method searches the given node iteratively
     * <p>
     *
     * @return boolean returns the boolean representation of the result
     */
    public boolean containsIterativeSearch(Node node) {
        if (head == null || node == null) return false;

        Node tempHead = head;
        while (tempHead != null) {
            if (tempHead.equals(node)) return true;
            tempHead = tempHead.next;
        }
        return false;
    }

    /**
     * Public method checks whether the linked list containsIterativeSearch the given node
     * The method searches the given node recursively
     * <p>
     *
     * @return boolean returns the boolean representation of the result
     */
    public boolean containsRecursiveSearch(Node node) {
        if (head == null || node == null) return false;

        if (head.equals(node)) {
            return true;
        } else {
            head = head.next;
            return containsRecursiveSearch(node);
        }
    }
    /**
     * Public method inserts the given node into the first place
     * <p>
     *
     * @param node node to be inserted to the first place
     * @return Node returns the added Node
     */
    public Node insertFirst(Node node) {
        //makes the old head current nodes head,
        //thus the given node will be the head
        node.next = head;

        //make the new node head
        head = node;
        //4increase the size of the linked list
        increaseSize();
        return head;
    }
}