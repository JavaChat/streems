package com.github.javachat.streems;

import com.github.javachat.streems.spliterators.BreadthFirstSpliterator;
import com.github.javachat.streems.spliterators.PostorderSpliterator;
import com.github.javachat.streems.spliterators.PreorderSpliterator;

import java.util.Arrays;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Streems
{
    private Streems()
    {
        throw new Error("no instantiation permitted");
    }

    public static <E> Stream<E> traverse(final E root,
        final IteratorProvider<E> provider, final Traversal traversal)
    {
        Objects.requireNonNull(traversal);

        final Spliterator<E> spliterator;

        switch (traversal) {
            case BREADTHFIRST:
                spliterator = new BreadthFirstSpliterator<>(root, provider);
                break;
            case POSTORDER:
                spliterator = new PostorderSpliterator<>(root, provider);
                break;
            case PREORDER:
                spliterator = new PreorderSpliterator<>(root, provider);
                break;
            default:
                throw new IllegalStateException();
        }

        return StreamSupport.stream(spliterator, false);
    }

    public static <E> Stream<E> traverse(final E root,
        final IterableProvider<E> provider, final Traversal traversal)
    {
        final IteratorProvider<E> p = e -> provider.apply(e).iterator();
        return traverse(root, p, traversal);
    }

    public static <E> Stream<E> traverse(final E root,
        final ArrayProvider<E> provider, final Traversal traversal)
    {
        final IteratorProvider<E> p
            = e -> Arrays.stream(provider.apply(root)).iterator();
        return traverse(root, p, traversal);
    }
}
