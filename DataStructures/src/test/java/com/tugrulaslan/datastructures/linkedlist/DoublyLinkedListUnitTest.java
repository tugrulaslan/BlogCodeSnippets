package com.tugrulaslan.datastructures.linkedlist;

import com.tugrulaslan.datastructures.linkedlist.doublylinkedlist.DoublyLinkedList;
import com.tugrulaslan.datastructures.linkedlist.doublylinkedlist.Node;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListUnitTest {

    private static DoublyLinkedList globalDoublyLinkedList;

    @Before
    public void init() {
        globalDoublyLinkedList = new DoublyLinkedList();
    }

    @Test
    public void isEmptyIsntEmptyTest() {
        fillLinedList();
        assertFalse("Linked list isn empty", globalDoublyLinkedList.isEmpty());
    }

    @Test
    public void isEmptyEmptyTest() {
        assertTrue("Linked list isn't null", globalDoublyLinkedList.isEmpty());
    }

    @Ignore
    @Test
    public void containsIterativeNullTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertFalse("Linkedlist's the head is not null", doublyLinkedList.containsIterativeSearch(null));
        doublyLinkedList.insertLast(new Node(1));
        assertFalse("Linkedlist containsIterativeSearch the given node or the head is not null", doublyLinkedList.containsIterativeSearch(null));
    }

    @Ignore
    @Test
    public void containsIterativeTrueTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        Node expected = new Node(1);
        doublyLinkedList.insertLast(expected);
        assertTrue("Linkedlist does not contain the given expected", doublyLinkedList.containsIterativeSearch(expected));
    }

    @Ignore
    @Test
    public void containsIterativeFalseTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        Node expected = new Node(1);
        doublyLinkedList.insertLast(expected);
        assertFalse("Linkedlist containsIterativeSearch the given node", doublyLinkedList.containsIterativeSearch(new Node(2)));
    }

    @Ignore
    @Test
    public void containsRecursiveNullTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        assertFalse("Linkedlist's the head is not null", doublyLinkedList.containsRecursiveSearch(null));
        doublyLinkedList.insertLast(new Node(1));
        assertFalse("Linkedlist containsIterativeSearch the given node or the head is not null", doublyLinkedList.containsIterativeSearch(null));
    }

    @Ignore
    @Test
    public void containsRecursiveTrueTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        Node expected = new Node(1);
        doublyLinkedList.insertLast(expected);
        assertTrue("Linkedlist does not contain the given expected", doublyLinkedList.containsRecursiveSearch(expected));
    }

    @Ignore
    @Test
    public void containsRecursiveFalseTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        Node node = new Node(1);
        doublyLinkedList.insertLast(node);
        assertFalse("Linkedlist containsIterativeSearch the given node", doublyLinkedList.containsRecursiveSearch(new Node(2)));
    }

    @Test
    public void insertFirstTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        Node thirdNode = new Node(3);
        doublyLinkedList.insertFirst(thirdNode);
        assertEquals(thirdNode, doublyLinkedList.head);

        Node secondNode = new Node(2);
        doublyLinkedList.insertFirst(secondNode);
        assertEquals(secondNode, doublyLinkedList.head);

        Node firstNode = new Node(1);
        doublyLinkedList.insertFirst(firstNode);
        assertEquals(firstNode, doublyLinkedList.head);
        assertEquals(null, doublyLinkedList.head.previous);
        assertEquals(secondNode, doublyLinkedList.head.next);

        //second node check
        assertEquals(firstNode, doublyLinkedList.head.next.previous);
        assertEquals(secondNode, doublyLinkedList.head.next);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);

        //third node check
        assertEquals(secondNode, doublyLinkedList.head.next.next.previous);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);
        assertEquals(null, doublyLinkedList.head.next.next.next);
    }

    @Test
    public void insertAfterTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        Node nullNode = null;
        Node expectedNullNode = doublyLinkedList.insertAfter(nullNode, null);
        assertNull("Expected is not null", expectedNullNode);

        Node thirdNode = new Node(3);
        doublyLinkedList.insertFirst(thirdNode);
        assertEquals(thirdNode, doublyLinkedList.head);

        Node firstNode = new Node(1);
        doublyLinkedList.insertFirst(firstNode);
        assertEquals(firstNode, doublyLinkedList.head);
        assertEquals(thirdNode, doublyLinkedList.head.next);

        Node secondNode = new Node(2);
        doublyLinkedList.insertAfter(firstNode, secondNode);
        assertEquals(firstNode, doublyLinkedList.head);
        assertEquals(secondNode, doublyLinkedList.head.next);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);

        //second node check
        assertEquals(firstNode, doublyLinkedList.head.next.previous);
        assertEquals(secondNode, doublyLinkedList.head.next);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);

        //third node check
        assertEquals(secondNode, doublyLinkedList.head.next.next.previous);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);
        assertEquals(null, doublyLinkedList.head.next.next.next);
    }

    @Test
    public void insertAfterInsertToEndTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        Node firstNode = new Node(1);
        doublyLinkedList.insertFirst(firstNode);

        Node secondNode = new Node(2);
        doublyLinkedList.insertAfter(firstNode, secondNode);
        assertEquals(firstNode, doublyLinkedList.head);
        assertEquals(secondNode, doublyLinkedList.head.next);

        assertNull(doublyLinkedList.head.previous);
        assertEquals(firstNode, doublyLinkedList.head.next.previous);

    }

    @Test
    public void insertLastTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        Node firstNode = new Node(1);
        doublyLinkedList.insertLast(firstNode);
        assertEquals(firstNode, doublyLinkedList.head);

        Node secondNode = new Node(2);
        doublyLinkedList.insertLast(secondNode);
        assertEquals(secondNode, doublyLinkedList.head.next);

        Node thirdNode = new Node(3);
        doublyLinkedList.insertLast(thirdNode);
        assertEquals(firstNode, doublyLinkedList.head);
        assertEquals(secondNode, doublyLinkedList.head.next);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);

        //second node check
        assertEquals(firstNode, doublyLinkedList.head.next.previous);
        assertEquals(secondNode, doublyLinkedList.head.next);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);

        //third node check
        assertEquals(secondNode, doublyLinkedList.head.next.next.previous);
        assertEquals(thirdNode, doublyLinkedList.head.next.next);
        assertEquals(null, doublyLinkedList.head.next.next.next);
    }

    @Test
    public void deleteFirstTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        assertNull(doublyLinkedList.deleteFirst());
        Node firstNode = new Node(1);
        doublyLinkedList.insertLast(firstNode);

        Node secondNode = new Node(2);
        doublyLinkedList.insertLast(secondNode);

        Node thirdNode = new Node(3);
        doublyLinkedList.insertLast(thirdNode);

        assertEquals(firstNode, doublyLinkedList.deleteFirst());
        assertEquals(secondNode, doublyLinkedList.head);
        assertNull(doublyLinkedList.head.previous);
        assertEquals(thirdNode, doublyLinkedList.head.next);
        assertEquals(secondNode, doublyLinkedList.head.next.previous);
        assertNull(doublyLinkedList.head.next.next);
    }

    @Test
    public void deleteNodeTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        assertNull(doublyLinkedList.deleteNode(null));
        Node firstNode = new Node(1);
        doublyLinkedList.insertLast(firstNode);

        Node secondNode = new Node(2);
        doublyLinkedList.insertLast(secondNode);

        Node thirdNode = new Node(3);
        doublyLinkedList.insertLast(thirdNode);

        Node deletedNode = doublyLinkedList.deleteNode(secondNode);
        assertEquals(secondNode, deletedNode);
        assertEquals(firstNode, doublyLinkedList.head);
        assertEquals(thirdNode, doublyLinkedList.head.next);
        assertNull(doublyLinkedList.head.next.next);

        //first node check
        assertEquals(firstNode, doublyLinkedList.head);

        //third node check
        assertEquals(thirdNode, doublyLinkedList.head.next);
        assertEquals(firstNode, doublyLinkedList.head.next.previous);
        assertNull(doublyLinkedList.head.next.next);

//        test last delete
        doublyLinkedList.insertAfter(thirdNode, secondNode);
        Node thirdNodeToBeDeleted = doublyLinkedList.deleteNode(thirdNode);
        assertEquals(thirdNode, thirdNodeToBeDeleted);
        assertEquals(firstNode, doublyLinkedList.head);
        assertNull(doublyLinkedList.head.previous);
        assertEquals(secondNode, doublyLinkedList.head.next);
        assertEquals(firstNode, doublyLinkedList.head.next.previous);
    }

    @Test
    public void deleteLastTest() {
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

        assertNull(doublyLinkedList.deleteLast());
        Node firstNode = new Node(1);
        doublyLinkedList.insertLast(firstNode);

        Node deletedFirstNode = doublyLinkedList.deleteLast();
        assertEquals(firstNode, deletedFirstNode);
        assertNull(doublyLinkedList.head);

        doublyLinkedList.insertLast(firstNode);

        Node secondNode = new Node(2);
        doublyLinkedList.insertLast(secondNode);

        Node thirdNode = new Node(3);
        doublyLinkedList.insertLast(thirdNode);

        Node deletedThirdNode = doublyLinkedList.deleteLast();
        assertEquals(thirdNode,deletedThirdNode);
        assertNull(doublyLinkedList.head.next.next);

    }


    private static void fillLinedList() {
        globalDoublyLinkedList = new DoublyLinkedList(new Node(1));
    }
}
