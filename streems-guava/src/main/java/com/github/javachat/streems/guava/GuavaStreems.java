package com.github.javachat.streems.guava;

import com.github.javachat.streems.Traversal;
import com.google.common.collect.TreeTraverser;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * N-ary tree node traversal via a {@link Stream} using a {@link TreeTraverser}
 * from Guava
 */
public final class GuavaStreems
{
    /**
     * Return a stream of nodes in a requested order
     *
     * @param root the root of the tree
     * @param traverser the {@link TreeTraverser} instance
     * @param traversal the requested traversal order
     * @param <E> type of the nodes in the tree
     * @return a stream of the nodes in the requested traversal order
     */
    public static <E> Stream<E> traverse(final E root,
        final TreeTraverser<E> traverser, final Traversal traversal)
    {
        final GuavaTraversal<E> gt = new GuavaTraversal<>(traverser);
        final Iterable<E> iterable = gt.forTraversal(traversal).apply(root);
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
