package com.tugrulaslan.datastructures.linkedlist;

public class Node {
    private int key;
    public Node next = null;

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                '}';
    }
}
