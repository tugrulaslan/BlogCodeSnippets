package com.tugrulaslan.datastructures.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTree {

    private Node rootNode;
    private final static int DATA_NOT_AVAILABLE = -1;

    /**
     * Public method that adds a new node for a given key
     * <p>
     *
     * @param key the key value to be inserted in the tree
     * @return Node returns the added Node
     */
    public Node addNode(int key) {
        rootNode = addNode(rootNode, key);
        return rootNode;
    }

    /**
     * Private method that adds a new node for a given key recursively.
     * <p>
     * If given node is empty it will be created,
     * The rest is a comparison of currentNode.key and key.
     * currentNode.key is the existing key in the structure,
     * key is the one that is newly being inserted.
     * <p>
     * If the given key is less than the existing key,
     * it will be placed on the left side of the tree,
     * else right side
     * <p>
     *
     * @param currentNode node to be traversed
     * @param key         the key value to be inserted in the tree
     * @return Node returns the added Node
     */
    private Node addNode(Node currentNode, int key) {
        if (currentNode == null)
            return new Node(key);

        if (key <= currentNode.key) {
            currentNode.leftNode = addNode(currentNode.leftNode, key);
        } else {
            currentNode.rightNode = addNode(currentNode.rightNode, key);
        }
        return currentNode;
    }

    /**
     * Public method that looks up the node for a given key.
     * If the key found then the related Node is returned
     * <p>
     *
     * @param key a value to be looked up in the tree
     * @return Node found node value
     */

    public Node containsKey(int key) {
        return containsKey(rootNode, key);
    }

    /**
     * Private method that looks up for a given key in a given node.
     * If the key found then the related Node is returned.
     * The method will look for the key on the left then
     * on the right side of the tree
     * <p>
     *
     * @param node the node to be lookedup
     * @param key  a value to be looked up in the tree
     * @return Node found node value
     */
    private Node containsKey(Node node, int key) {
        if (node == null) {
            return null;
        } else if (key == node.key) {
            return node;
        } else {
            return key <= node.key ? containsKey(node.leftNode, key) : containsKey(node.rightNode, key);
        }
    }

    /**
     * Public method informs whether the tree is empty or not
     * <p>
     *
     * @return boolean the outcome of the tree capacity in a boolean value
     */
    public boolean isTreeEmpty() {
        return rootNode == null;
    }

    /**
     * Public method returns the maximum height of the tree
     *
     * @return int maximum value of the tree
     */
    public int getTreeHeight() {
        return getTreeHeight(rootNode);
    }

    /**
     * Private method that returns the maximum height of the left, right heights of the tree
     * The method is recursively called from the both sides
     *
     * @param node given node to be traversed for the height calculation
     * @return int value for the calculated tree's maximum height.
     */
    private int getTreeHeight(Node node) {
        if (node == null)
            return 0;
        int leftTreeHeight = getTreeHeight(node.leftNode);
        int rightTreeHeight = getTreeHeight(node.rightNode);
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }

    /**
     * Public method returns the minimum value in the tree,
     * If the tree is null then, returns -1
     *
     * @return int value of the minimum value, returns -1 for the null tree
     */
    public int getMinimumValue() {
        if (rootNode == null)
            return DATA_NOT_AVAILABLE;
        return getMinimumValue(rootNode);
    }

    /**
     * Private method that recursively looks for the smallest value on the left side of the tree
     * returns the value from the deepest level of the tree.
     */
    private int getMinimumValue(Node rootNode) {
        return rootNode.leftNode == null ? rootNode.key : getMinimumValue(rootNode.leftNode);
    }

    /**
     * Public method returns the maximum, value in the tree,
     * If the tree is null then, returns -1
     *
     * @return int value of the maximum value, returns -1 for the null tree
     */
    public int getMaximumValue() {
        if (rootNode == null)
            return DATA_NOT_AVAILABLE;
        return getMaximumValue(rootNode);
    }

    /**
     * Private method that recursively looks for the greatest value on the right side of the tree
     * returns the value from the deepest level of the tree.
     */
    private int getMaximumValue(Node rootNode) {
        return rootNode.rightNode == null ? rootNode.key : getMaximumValue(rootNode.rightNode);
    }

    /**
     * Public method of In Order Traversal, directly calls the private method
     * that recursively prints out the Node content.
     */
    public void inOrderTraversal() {
        System.out.println("***In Order Traversal***\n");
        inOrderTraversal(rootNode);
    }

    /**
     * public method that traverses the tree in the in order traversal fashion.
     * In Order Traversal sequence is:
     * 1. Left Node,
     * 2. Root Node,
     * 3. Right Node
     * <p>
     *
     * @param node the node to be traversed
     */
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.leftNode);
            System.out.println(node.key);
            inOrderTraversal(node.rightNode);
        }
    }

    /**
     * Public method of Pre Order Traversal, directly calls the private method
     * that recursively prints out the Node content.
     */
    public void preOrderTraversal() {
        System.out.println("***Pre Order Traversal***\n");
        preOrderTraversal(rootNode);
    }

    /**
     * public method that traverses the tree in the pre order traversal fashion.
     * In Order Traversal sequence is:
     * 1. Root  Node,
     * 2. Left Node,
     * 3. Right Node
     * <p>
     *
     * @param node the node to be traversed
     */
    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrderTraversal(node.leftNode);
            preOrderTraversal(node.rightNode);
        }
    }

    /**
     * Public method of Post Order Traversal, directly calls the private method
     * that recursively prints out the Node content.
     */
    public void postOrderTraversal() {
        System.out.println("***Post Order Traversal***\n");
        postOrderTraversal(rootNode);
    }

    /**
     * Private method that traverses the tree in the post order traversal fashion.
     * In Order Traversal sequence is:
     * 1. Left Node,
     * 2. Right Node,
     * 3. Root Node
     * <p>
     *
     * @param node the node to be traversed
     */
    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.leftNode);
            postOrderTraversal(node.rightNode);
            System.out.println(node.key);
        }
    }

    /**
     * Public method prints the content of the tree in a level way.
     * All the node is added to the queue then printed out level by level
     */
    public void breadthFirstTraversal() {
        if (rootNode == null)
            return;
        System.out.println("***Breadth First Traversal***\n");
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(rootNode);
        while (!queue.isEmpty()) {
            rootNode = queue.remove();
            System.out.println(rootNode.key);
            if (rootNode.leftNode != null)
                queue.add(rootNode.leftNode);
            if (rootNode.rightNode != null)
                queue.add(rootNode.rightNode);
        }
    }

}