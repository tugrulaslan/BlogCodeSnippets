package com.tugrulaslan.binarytree;

import com.tugrulaslan.datastructures.binarytree.BinaryTree;
import com.tugrulaslan.datastructures.binarytree.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeUnitTest {

    private static int[] TEST_DATA;
    private BinaryTree binaryTree;

    @Before
    public void init() {
        TEST_DATA = new int[]{60, 55, 67, 50, 57, 61, 77, 45, 53};
        binaryTree = new BinaryTree();
    }

    @Test
    public void addNodeTest() {
        for (int currentNumber : TEST_DATA) {
            Node actualNode = binaryTree.addNode(currentNumber);
            assertNotNull("the node is null", actualNode);
        }
    }

    @Test
    public void isTreeEmptyTestTrue() {
        assertTrue("tree is not empty", binaryTree.isTreeEmpty());
    }

    @Test
    public void isTreeEmptyTestFalse() {
        fillTestData();
        assertFalse("tree is null", binaryTree.isTreeEmpty());
    }

    @Test
    public void containsKeyTestNull() {
        assertNull("the node is not null", binaryTree.containsKey(9999));
    }

    @Test
    public void containsKeyTestFalse() {
        assertNull("the key exists in the node", binaryTree.containsKey(9999));
    }

    @Test
    public void containsKeyTestTrue() {
        fillTestData();
        assertNotNull("the key does not exist in the node", binaryTree.containsKey(55));
    }

    @Test
    public void maxTreeDepthTestNull() {
        int actual = binaryTree.getTreeHeight();
        assertEquals(0, actual);
    }

    @Test
    public void maxTreeDepthTesActual() {
        fillTestData();
        int actual = binaryTree.getTreeHeight();
        assertEquals(4, actual);
    }

    @Test
    public void inOrderTraversalTest() {
        fillTestData();
        binaryTree.inOrderTraversal();
    }

    @Test
    public void preOrderTraversalTest() {
        fillTestData();
        binaryTree.preOrderTraversal();
    }

    @Test
    public void postOrderTraversalTest() {
        fillTestData();
        binaryTree.postOrderTraversal();
    }

    @Test
    public void breadthFirstTraversalTest() {
        fillTestData();
        binaryTree.breadthFirstTraversal();
    }

    @Test
    public void getMinimumValueTestNull() {
        int actual = binaryTree.getMinimumValue();
        assertEquals("the tree is not null", -1, actual);
    }

    @Test
    public void getMinimumValueTestNonNull() {
        fillTestData();
        int actual = binaryTree.getMinimumValue();
        assertEquals("the tree is not instantiated", 45, actual);
    }
    @Test
    public void getMaximumValueTestNull() {
        int actual = binaryTree.getMaximumValue();
        assertEquals("the tree is not null", -1, actual);
    }

    @Test
    public void getMaximumValueTestNonNull() {
        fillTestData();
        int actual = binaryTree.getMaximumValue();
        assertEquals("the tree is not instantiated", 77, actual);
    }

    private void fillTestData() {
        for (int currentNumber : TEST_DATA) {
            binaryTree.addNode(currentNumber);
        }
    }
}