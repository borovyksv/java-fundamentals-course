package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
@NoArgsConstructor
public class LinkedQueue<T> implements Queue<T> {

    @RequiredArgsConstructor
    private static class Node<T> {
        final T value;
        Node<T> next;
    }

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(@NonNull T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail = tail.next = newNode;
        }
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        if (isEmpty()) return null;
        var currentHead = head;
        head = currentHead.next;
        size--;
        if (head == null) tail = null;
        return currentHead.value;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
