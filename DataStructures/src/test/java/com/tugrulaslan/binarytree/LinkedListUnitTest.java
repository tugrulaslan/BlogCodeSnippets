package com.tugrulaslan.binarytree;

import com.tugrulaslan.datastructures.linkedlist.LinkedList;
import com.tugrulaslan.datastructures.linkedlist.Node;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class LinkedListUnitTest {

    private static LinkedList linkedList;

    @Before
    public void init() {
        linkedList = new LinkedList();
    }

    @Test
    public void test() {
        LinkedList linkedList = new LinkedList();

        linkedList.head = new Node(1);
        Node second = new Node(2);
        linkedList.head.next = second;
        Node third = new Node(3);
        second.next = third;
        linkedList.printList();
    }

    @Test
    public void isEmptyIsntEmptyTest() {
        fillLinedList();
        assertFalse("Linked list isn empty", linkedList.isEmpty());
    }

    @Test
    public void isEmptyEmptyTest() {
        assertTrue("Linked list isn't null", linkedList.isEmpty());
    }

    @Test
    public void

    private static void fillLinedList(){
        linkedList = new LinkedList(new Node(1));
    }
}
