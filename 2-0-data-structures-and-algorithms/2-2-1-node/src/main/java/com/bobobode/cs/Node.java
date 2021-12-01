package com.bobobode.cs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Class {@link Node} is a very simple data structure that consists of an element itself and the reference to the next
 * node. An element can have any value since it's a generic. A reference to the next node allows to link {@link Node}
 * objects and build more comprehensive data structures on top of those liked nodes.
 *
 * @author Taras Boychuk
 */

@Data
@RequiredArgsConstructor
public final class Node<T> {
    private final T value;
    private Node<T> next;
}
