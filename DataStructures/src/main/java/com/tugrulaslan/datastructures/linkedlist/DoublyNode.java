package com.tugrulaslan.datastructures.linkedlist;

import java.util.Objects;

public class DoublyNode extends Node {
    public Node previous = null;
    public DoublyNode(int key) {
        super(key);
    }

    @Override
    public String toString() {
        return "DoublyNode{}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DoublyNode that = (DoublyNode) o;
        return Objects.equals(previous, that.previous);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), previous);
    }
}
