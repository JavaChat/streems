package com.github.javachat.streems;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Functional interface used by a node providing a {@link Stream} of its
 * children
 *
 * @param <E> the type of nodes in the tree
 */
@FunctionalInterface
public interface StreamProvider<E>
    extends Function<E, Stream<E>>
{
}
