package com.github.javachat.streems;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Functional interface used by a node providing an {@link Iterator} of its
 * children
 *
 * @param <E> the type of nodes in the tree
 */
@FunctionalInterface
public interface IteratorProvider<E>
    extends Function<E, Iterator<E>>
{
}
