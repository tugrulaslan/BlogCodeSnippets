package com.tugrulaslan.datastructures.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class SinglyLinkedListUnitTest {

    private static SinglyLinkedList globalSinglyLinkedList;

    @Before
    public void init() {
        globalSinglyLinkedList = new SinglyLinkedList();
    }

    @Test
    public void isEmptyIsntEmptyTest() {
        fillLinedList();
        assertFalse("Linked list isn empty", globalSinglyLinkedList.isEmpty());
    }

    @Test
    public void isEmptyEmptyTest() {
        assertTrue("Linked list isn't null", globalSinglyLinkedList.isEmpty());
    }

    @Test
    public void containsIterativeNullTest(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        assertFalse("Linkedlist's the head is not null", singlyLinkedList.containsIterativeSearch(null));
        singlyLinkedList.insertLast(new Node(1));
        assertFalse("Linkedlist containsIterativeSearch the given node or the head is not null", singlyLinkedList.containsIterativeSearch(null));
    }

    @Test
    public void containsIterativeTrueTest(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        Node expected = new Node(1);
        singlyLinkedList.insertLast(expected);
        assertTrue("Linkedlist does not contain the given expected", singlyLinkedList.containsIterativeSearch(expected));
    }

    @Test
    public void containsIterativeFalseTest(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        Node node = new Node(1);
        singlyLinkedList.insertLast(node);
        assertFalse("Linkedlist containsIterativeSearch the given node", singlyLinkedList.containsIterativeSearch(new Node(2)));
    }
    @Test
    public void containsRecursiveNullTest(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        assertFalse("Linkedlist's the head is not null", singlyLinkedList.containsRecursiveSearch(null));
        singlyLinkedList.insertLast(new Node(1));
        assertFalse("Linkedlist containsIterativeSearch the given node or the head is not null", singlyLinkedList.containsIterativeSearch(null));
    }

    @Test
    public void containsRecursiveTrueTest(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        Node expected = new Node(1);
        singlyLinkedList.insertLast(expected);
        assertTrue("Linkedlist does not contain the given expected", singlyLinkedList.containsRecursiveSearch(expected));
    }

    @Test
    public void containsRecursiveFalseTest(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        Node node = new Node(1);
        singlyLinkedList.insertLast(node);
        assertFalse("Linkedlist containsIterativeSearch the given node", singlyLinkedList.containsRecursiveSearch(new Node(2)));
    }

    @Test
    public void insertFirstTest() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        Node thirdNode = new Node(3);
        singlyLinkedList.insertFirst(thirdNode);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head);

        Node secondNode = new Node(2);
        singlyLinkedList.insertFirst(secondNode);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head);

        Node firstNode = new Node(1);
        singlyLinkedList.insertFirst(firstNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next.next);
    }

    @Test
    public void insertAfterTest() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        Node nullNode = null;
        Node expectedNullNode = singlyLinkedList.insertAfter(nullNode, null);
        assertNull("Expected is not null", expectedNullNode);

        Node thirdNode = new Node(3);
        singlyLinkedList.insertFirst(thirdNode);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head);

        Node firstNode = new Node(1);
        singlyLinkedList.insertFirst(firstNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next);

        Node secondNode = new Node(2);
        singlyLinkedList.insertAfter(firstNode, secondNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next.next);
    }

    @Test
    public void insertLastTest() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        Node firstNode = new Node(1);
        singlyLinkedList.insertLast(firstNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);

        Node secondNode = new Node(2);
        singlyLinkedList.insertLast(secondNode);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);

        Node thirdNode = new Node(3);
        singlyLinkedList.insertLast(thirdNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next.next);
    }

    @Test
    public void deleteFirstTest() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        assertNull("Linked list's head is not null", singlyLinkedList.deleteFirst());
        Node firstNode = new Node(1);
        singlyLinkedList.insertLast(firstNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);

        Node secondNode = new Node(2);
        singlyLinkedList.insertLast(secondNode);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);

        Node thirdNode = new Node(3);
        singlyLinkedList.insertLast(thirdNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next.next);

        assertEquals("The deleted node isn't the first node", firstNode, singlyLinkedList.deleteFirst());
        assertEquals("the first node isn't the second node", secondNode, singlyLinkedList.head);
        assertEquals("the second node isn't the third node", thirdNode, singlyLinkedList.head.next);
    }

    @Test
    public void deleteNodeTest() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        assertNull("Linked list's head is not null", singlyLinkedList.deleteNode(null));
        Node firstNode = new Node(1);
        singlyLinkedList.insertLast(firstNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);

        Node secondNode = new Node(2);
        singlyLinkedList.insertLast(secondNode);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);

        Node thirdNode = new Node(3);
        singlyLinkedList.insertLast(thirdNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next.next);

        Node deletedNode = singlyLinkedList.deleteNode(secondNode);
        assertEquals("The deleted node isn't the second node", secondNode, deletedNode);
        assertEquals("the first node isn't the first node", firstNode, singlyLinkedList.head);
        assertEquals("the second node isn't the third node", thirdNode, singlyLinkedList.head.next);
        assertNull("the third node isn't null", singlyLinkedList.head.next.next);

        //test last delete
        singlyLinkedList.insertAfter(thirdNode, secondNode);
        Node thirdNodeToBeDeleted = singlyLinkedList.deleteNode(thirdNode);
        assertEquals("The deleted node isn't the third node", thirdNode, thirdNodeToBeDeleted);
        assertEquals("the first node isn't the first node", firstNode, singlyLinkedList.head);
        assertEquals("the second node isn't the second node", secondNode, singlyLinkedList.head.next);

    }

    @Test
    public void deleteLastTest() {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        assertNull("Linked list's head is not null", singlyLinkedList.deleteLast());
        Node firstNode = new Node(1);
        singlyLinkedList.insertLast(firstNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);

        Node deletedFirstNode = singlyLinkedList.deleteLast();
        assertEquals("The deleted node isn't the first node", firstNode, deletedFirstNode);

        singlyLinkedList.insertLast(firstNode);

        Node secondNode = new Node(2);
        singlyLinkedList.insertLast(secondNode);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);

        Node thirdNode = new Node(3);
        singlyLinkedList.insertLast(thirdNode);
        assertEquals("the given node is not the first", firstNode, singlyLinkedList.head);
        assertEquals("the given node is not the second", secondNode, singlyLinkedList.head.next);
        assertEquals("the given node is not the third", thirdNode, singlyLinkedList.head.next.next);

        Node deletedThirdNode = singlyLinkedList.deleteLast();
        assertEquals("The deleted node isn't the third node", thirdNode, deletedThirdNode);
        assertEquals("the first node isn't the first node", firstNode, singlyLinkedList.head);
        assertEquals("the second node isn't the second node", secondNode, singlyLinkedList.head.next);
        assertNull("the third node isn't null", singlyLinkedList.head.next.next);

    }


    private static void fillLinedList() {
        globalSinglyLinkedList = new SinglyLinkedList(new Node(1));
    }
}
