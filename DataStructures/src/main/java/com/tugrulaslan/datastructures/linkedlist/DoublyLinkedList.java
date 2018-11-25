package com.tugrulaslan.datastructures.linkedlist;

public class DoublyLinkedList extends LinkedList {

    public DoublyLinkedList() {

    }

    public DoublyLinkedList(DoublyNode doublyNode) {
        this.head = doublyNode;

    }

    /**
     * Public method inserts the given doublyNode to the very end
     * <p>
     *
     * @param doublyNode doublyNode that will be inserted to the end.
     * @return Node returns the added Node
     */
    public Node insertLast(DoublyNode doublyNode) {
        //if the head is null then given is the head
        if (head == null) {
            head = doublyNode;
            increaseSize();
            return doublyNode;
        }
        //traverse through the nodes
        //1. create a temp head doublyNode which will be the starting point
        Node tempHead = head;

        //2. iterate through while the next element is null
        while (tempHead.next != null) {
            //assign the next element as the tempHead so that the while will continue until the last doublyNode's next is null
            tempHead = tempHead.next;
        }
        //3.the last doublyNode's next is null, thus the null will be replaced with the given doublyNode
        tempHead.next = doublyNode;

        //4. assign tempHead as doubly nodes previous
        doublyNode.previous = tempHead;

        //5.increase the size of the linked list
        increaseSize();
        return tempHead;
    }


}
