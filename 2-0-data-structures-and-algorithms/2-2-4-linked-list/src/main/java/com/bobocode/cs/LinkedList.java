package com.bobocode.cs;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
@NoArgsConstructor
public class LinkedList<T> implements List<T> {

    @RequiredArgsConstructor
    private static class Node<T> {
        final T value;
        Node<T> next;
    }

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> linkedList = new LinkedList<>();
        Stream.of(elements).forEach(linkedList::add);
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        add(size, element);
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) throw new IndexOutOfBoundsException();

        var newNode = new Node<T>(element);
        if (size == 0) {
            head = tail = newNode;
        }
        if (size == 1) {
            tail = tail.next = newNode;
        }
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            var currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
        if (size < 2) {
            add(0, element);
        }

        var newNode = new Node<T>(element);
        if (index == 0) {
            newNode.next = head.next;
            head = newNode;
        } else {
            var currentNode = head;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next.next;
            currentNode.next = newNode;
        }
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (isEmpty() || index >= size) throw new IndexOutOfBoundsException();
        var current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return head.value;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return tail.value;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if (isEmpty() || index >= size) throw new IndexOutOfBoundsException();
        T value;
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            var current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            value = current.next.value;
            current.next = current.next.next;
        }
        size--;
        return value;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        var current = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }
}
