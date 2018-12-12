package com.tugrulaslan.datastructures.linkedlist.doublylinkedlist;

import com.tugrulaslan.datastructures.linkedlist.LinkedList;

public class DoublyLinkedList extends LinkedList {

    public Node head;

    public DoublyLinkedList() {

    }

    public DoublyLinkedList(Node node) {
        this.head = node;
    }

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
     * shuffles previous heads as well
     * <p>
     *
     * @param node node to be inserted to the first place
     * @return Node returns the added Node
     */
    public Node insertFirst(Node node) {
        //makes the old head current nodes head,
        //thus the given node will be the head
        node.next = head;

        //make old head's previous new inserted node
        if (head != null) head.previous = node;

        //make replace the old head with the new node
        head = node;
        //increase the size of the linked list
        increaseSize();
        return head;
    }

    /**
     * Public method inserts the given node after the given node
     * <p>
     *
     * @param previousNode node whose next will be replaced
     * @param newNode      node that will be the next and will have the previous node's next nodes.
     * @return Node returns the added Node
     */
    public Node insertAfter(Node previousNode, Node newNode) {
        if (previousNode == null || newNode == null) return null;

        /**
         * in the first and second assignments, we are swapping each of new and previous node's next pointers
         * */
        //set previous nodes's next to new node's next
        newNode.next = previousNode.next;

        //set new node to previous node's next
        previousNode.next = newNode;

        //sets previous node to new nodes previous
        newNode.previous = previousNode;

        /**
         move previous node's next to
         */
        if (newNode.next != null) newNode.next.previous = newNode;

        //increase the size of the linked list
        increaseSize();
        return newNode;
    }

    /**
     * Public method inserts the given node to the very end
     * <p>
     *
     * @param node node that will be inserted to the end.
     * @return Node returns the added Node
     */
    public Node insertLast(Node node) {
        //if the head is null then given is the head
        if (head == null) {
            node.previous = null;
            head = node;
            increaseSize();
            return node;
        }

        //traverse through the nodes
        //1. create a temp head node which will be the starting point
        Node tempHead = head;

        //2. iterate through while the next element is null
        while (tempHead.next != null) {
            //assign the next element as the tempHead so that the while will continue until the last node's next is null
            tempHead = tempHead.next;
        }
        //3.assign the new node to temp head's next.
        tempHead.next = node;

        //4. assign tempHead as doubly nodes previous
        node.previous = tempHead;

        //5.increase the size of the linked list
        increaseSize();
        return node;
    }

    /**
     * Public method deletes the head node
     * <p>
     *
     * @return Node returns the deleted head Node
     */
    public Node deleteFirst() {
        //Assign the head to the temp head
        Node tempHead = null;
        if (!isEmpty()) {
            tempHead = head;
            //Make the main head temp heads next
            //thus the main head will be null and garbage collected
            head = head.next;
            //nullify new head's previous
            if (head != null) head.previous = null;
            decreaseSize();
        }
        return tempHead;
    }

    /**
     * Public method deletes the head node
     * <p>
     *
     * @param nodeToBeDeleted the node to be deleted
     * @return Node returns the deleted head Node
     */
    public Node deleteNode(Node nodeToBeDeleted) {
        //If the head is null return null
        if (isEmpty() || nodeToBeDeleted == null) return null;

        //if the given is head, delete the head
        if (head.equals(nodeToBeDeleted)) {
            decreaseSize();
            return deleteFirst();
        }

        //traverse and look for the given node
        //1.assign tempHead to the head
        //2. assign null to the previous node
        Node tempHead = head;
        Node previousNode = null;
        while (tempHead != null) {
            //if the temp head is equal to the given node
            //assign temp head's next to the previous nodes next
            //and exit the while loop
            if (tempHead.equals(nodeToBeDeleted)) {
                //assign deleted node's next to deleted node's previous
                previousNode.next = tempHead.next;
                //replace deleted node's place/pointer with deleted node's next element
                tempHead = tempHead.next;
                //deleted pointer's previous element is replaced with deleted previous node's previous
                tempHead.previous = previousNode;
                decreaseSize();
                return nodeToBeDeleted;
            }
            // the node was not found in this iteration
            // assign temp head next to the previous node
            // assign temp head's next to the temp head to continue the iteration
            previousNode = tempHead;
            tempHead = tempHead.next;
        }
        return tempHead;
    }

    /**
     * Public method deletes node in the end and returns it
     * <p>
     *
     * @return Node deleted node
     */
    public Node deleteLast() {
        //if the head is null return null
        if (isEmpty()) return null;

        //if size is equals to one, it means we only have the head
        if (head.next == null) return deleteFirst();

        //1.create tempHead that points to the head
        Node tempHead = head;
        //2.create previous that will remember the previous node
        Node previous = head;
        //3.create variable that will point to the deleted node
        Node deletedNode = null;

        //4.iterate through the nodes until the temp head's next is null
        while (tempHead.next != null) {
            //5.assign tempHead to previous
            previous = tempHead;
            //6.assign tempHead's next to temp head to previous
            tempHead = tempHead.next;
        }
        //7. point previous next which is the node to be deleted
        deletedNode = previous.next;
        //8.detach the previous next node which is the last node
        previous.next = null;
        //9.decrease the size of the linkedlist
        decreaseSize();
        return deletedNode;
    }
}
