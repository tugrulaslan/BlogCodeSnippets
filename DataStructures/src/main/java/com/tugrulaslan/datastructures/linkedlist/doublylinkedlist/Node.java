package com.tugrulaslan.datastructures.linkedlist.doublylinkedlist;

import java.util.Objects;

public class Node {
    private int key;
    public Node next = null;
    public Node previous = null;

    public Node() {
    }

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node that = (Node) o;
        return key == that.key &&
                Objects.equals(next, that.next) &&
                Objects.equals(previous, that.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, next, previous);
    }

}
