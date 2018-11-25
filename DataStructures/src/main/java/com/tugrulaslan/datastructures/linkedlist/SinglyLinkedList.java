package com.tugrulaslan.datastructures.linkedlist;

public class SinglyLinkedList extends LinkedList {


    public SinglyLinkedList() {
    }

    public SinglyLinkedList(Node head) {
        this.head = head;
    }

    /**
     * Public method inserts the given node after the given node
     * <p>
     *
     * @param previousNode node whose next will be replaced
     * @param givenNode    node that will be the next and will have the previous node's next nodes.
     * @return Node returns the added Node
     */
    public Node insertAfter(Node previousNode, Node givenNode) {
        if (previousNode == null) return null;

        //make previous node's next node, given's next node
        givenNode.next = previousNode.next;

        //make given node, previous node's next
        previousNode.next = givenNode;

        //increase the size of the linked list
        increaseSize();
        return givenNode;
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
        //3.the last node's next is null, thus the null will be replaced with the given node
        tempHead.next = node;
        //4.increase the size of the linked list
        increaseSize();
        return tempHead;
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
        if (isEmpty()) return null;

        //if the given
        if (nodeToBeDeleted == null) return null;

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
                previousNode.next = tempHead.next;
                decreaseSize();
                break;
            }
            // the node was not found in this iteration
            // assign temp head next to the previous node
            // assign temp head's next to the temp head to continue the iteration
            previousNode = tempHead;
            tempHead = tempHead.next;
            decreaseSize();
        }
        return tempHead;
    }

    public Node deleteLast() {
        //if the head is null return null
        if (isEmpty()) return null;

        //if size is equals to one, it means we only have the head
        if (size() == 1) return deleteFirst();

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