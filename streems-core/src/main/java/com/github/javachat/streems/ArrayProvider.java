package com.github.javachat.streems;

import java.util.function.Function;

/**
 * Functional interface used by a node providing an array of its children
 *
 * @param <E> the type of nodes in the tree
 */
@FunctionalInterface
public interface ArrayProvider<E>
    extends Function<E, E[]>
{
}
