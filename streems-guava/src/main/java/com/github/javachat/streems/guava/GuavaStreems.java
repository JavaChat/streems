package com.github.javachat.streems.guava;

import com.github.javachat.streems.Traversal;
import com.google.common.collect.TreeTraverser;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class GuavaStreems
{

    public static <E> Stream<E> traverse(final E root,
        final TreeTraverser<E> traverser, final Traversal traversal)
    {
        final GuavaTraversal<E> gt = new GuavaTraversal<>(traverser);
        final Iterable<E> iterable = gt.forTraversal(traversal).apply(root);
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
