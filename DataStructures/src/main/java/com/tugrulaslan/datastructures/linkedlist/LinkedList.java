package com.tugrulaslan.datastructures.linkedlist;

public class LinkedList {

    public Node head;

    public LinkedList() {
    }

    public LinkedList(Node head) {
        this.head = head;
    }

    /**
     * Public method checks whether the linked list is empty or not
     * <p>
     *
     * @return Node returns the added Node
     */
    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Public method inserts the given node into the first place
     * <p>
     *
     * @param node node to be inserted to the first place
     * @return Node returns the added Node
     */
    public Node insertFirst(Node node){
        head.next = node;
        head = node;
        return node;
    }

    public Node removeFirst(){
        Node nodeRef = head;
        if(!isEmpty()){
            head = head.next;
        }
        return nodeRef;
    }

    public void printList() {
        Node node = head;
        while (node != null){
            Node nextNode = node.next;
            System.out.println(node);
            node = nextNode;
        }
    }
}
