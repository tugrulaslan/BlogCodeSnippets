package com.tugrulaslan.datastructures.queue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QueueUnitTest {

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
    public void emptyQueueIsEmptyTest() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void nonQueueStackIsEmptyTest() {
        Queue queue = filledQueue();
        assertFalse("Stack is empty", queue.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyQueuePeekTest() {
        Queue queue = new Queue();
        queue.peek();
    }

    @Test
    public void nonEmptyQueuePeekTest() {
        Queue queue = filledQueue();
        final Node actual = queue.peek();
        assertThat(actual).isEqualTo(mockedNodeOne);
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyQueueDequeueTest() {
        Queue queue = new Queue();
        queue.dequeue();
    }

    @Test
    public void dequeueOneElementFromQueueTest() {
        Queue queue = filledQueue();
        final Node actual = queue.dequeue();
        assertThat(actual).isEqualTo(mockedNodeOne);
        assertTrue("queue is not empty", queue.isEmpty());
    }

    @Test
    public void dequeueTwoElementFromQueueTest() {
        Queue queue = filledQueue();
        queue.enqueue(mockedNodeTwo);
        final Node actualFirstNode = queue.dequeue();
        assertThat(actualFirstNode).isEqualTo(mockedNodeOne);
        final Node actualSecondNode = queue.dequeue();
        assertThat(actualSecondNode).isEqualTo(mockedNodeTwo);
        assertTrue("queue is not empty", queue.isEmpty());
    }

    @Test
    public void dequeueElementsFromQueueTest() {
        Queue queue = filledQueue();
        queue.enqueue(mockedNodeTwo);
        queue.enqueue(mockedNodeThree);
        final Node actualFirstNode = queue.dequeue();
        assertThat(actualFirstNode).isEqualTo(mockedNodeOne);
        final Node actualSecondNode = queue.dequeue();
        assertThat(actualSecondNode).isEqualTo(mockedNodeTwo);
        final Node actualThirdNode = queue.dequeue();
        assertThat(actualThirdNode).isEqualTo(mockedNodeThree);
        assertTrue("queue is not empty", queue.isEmpty());
    }

    private static Queue filledQueue() {
        Queue queue = new Queue();
        queue.enqueue(mockedNodeOne);
        return queue;
    }
}
