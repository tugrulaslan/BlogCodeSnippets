package com.tugrulaslan.datastructures.stack;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StackUnitTest {

    private static Node mockedNodeOne;
    private static Node mockedNodeTwo;
    private static Node mockedNodeThree;

    @Before
    public void setup() {
        mockedNodeOne = new Node(1);
        mockedNodeTwo = new Node(2);
        mockedNodeThree = new Node(3);
    }

    @Test
    public void emptyStackIsEmptyTest() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void nonEmptyStackIsEmptyTest() {
        Stack stack = filledStack();
        assertFalse("Stack is empty", stack.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyStackPeekTest() {
        Stack stack = new Stack();
        stack.pop();
    }

    @Test
    public void nonEmptyStackPeekTest() {
        Stack stack = filledStack();
        final int actual = stack.peek();
        assertThat(actual).isEqualTo(mockedNodeOne.data);
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyStackPopTest() {
        Stack stack = new Stack();
        stack.pop();
    }

    @Test
    public void popOneElementFromStackTest() {
        Stack stack = filledStack();
        final int actual = stack.pop();
        assertThat(actual).isEqualTo(mockedNodeOne.data);
        assertTrue("stack is not empty", stack.isEmpty());
    }

    @Test
    public void popTwoElementsFromStackTest() {
        Stack stack = filledStack();
        stack.push(mockedNodeTwo);
        stack.push(mockedNodeThree);
        final int actualThirdNode = stack.pop();
        assertThat(actualThirdNode).isEqualTo(mockedNodeThree.data);
        final int actualSecondNode = stack.pop();
        assertThat(actualSecondNode).isEqualTo(mockedNodeTwo.data);
        final int actualFirstNode = stack.pop();
        assertThat(actualFirstNode).isEqualTo(mockedNodeOne.data);
        assertTrue("stack is not empty", stack.isEmpty());
    }

    @Test
    public void popThreeElementsFromStackTest() {
        Stack stack = filledStack();
        stack.push(mockedNodeTwo);
        final int actualSecondNode = stack.pop();
        assertThat(actualSecondNode).isEqualTo(mockedNodeTwo.data);
        final int actualFirstNode = stack.pop();
        assertThat(actualFirstNode).isEqualTo(mockedNodeOne.data);
        assertTrue("stack is not empty", stack.isEmpty());
    }

    private static Stack filledStack() {
        Stack stack = new Stack();
        stack.push(mockedNodeOne);
        return stack;
    }
}
