package com.github.javachat.streems;

import com.github.javachat.streems.guava.GuavaTraversal;
import com.google.common.collect.TreeTraverser;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Streems
{
    private Streems()
    {
        throw new Error("no instantiation permitted");
    }

    public static <E> Stream<E> fromGuava(final E root,
        final TreeTraverser<E> traverser, final Traversal traversal)
    {
        final GuavaTraversal<E> gt = new GuavaTraversal<>(traverser);
        final Iterable<E> iterable = gt.forTraversal(traversal).apply(root);
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
