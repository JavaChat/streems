package com.github.javachat.streems.spliterators;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Generic spliterator for n-ary tree traversal
 *
 * <p>Note that null elements are not allowed anywhere in the tree.</p>
 *
 * <p>This abstract class has default, fixed implementations of the following
 * methods:</p>
 *
 * <ul>
 *     <li>{@link #trySplit()} always returns {@code null};</li>
 *     <li>{@link #estimateSize()} always returns {@link Long#MAX_VALUE};</li>
 *     <li>{@link #characteristics()} are always {@link Spliterator#ORDERED
 *     ORDERED} and {@link Spliterator#NONNULL NONNULL}.</li>
 * </ul>
 *
 * <p>Internally, the logic of traversal will always use an {@link Iterator}.
 * The function as an argument to the constructor is the means by which the
 * implementations will walk the children.</p>
 *
 * <p>The transformations from the three other possibilities ({@link Iterable},
 * {@link Stream} and arrays) are as follows:</p>
 *
 * <ul>
 *     <li>{@link Iterable}: call the {@link Iterable#iterator()} method;</li>
 *     <li>{@link Stream}: call this stream's {@link Stream#iterator()
 *     iterator()};</li>
 *     <li>array: call {@code Arrays.stream(theArray).iterator()}.</li>
 * </ul>
 *
 * @param <T> type of the nodes in the tree
 */
public abstract class NaryTreeSpliterator<T>
    implements Spliterator<T>
{
    /**
     * The function providing an iterator of children (in order) from a node
     */
    protected final Function<T, Iterator<T>> fn;

    /**
     * Constructor
     *
     * @param root the root node (cannot be null)
     * @param fn provider for an iterator of a node's children
     */
    protected NaryTreeSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        Objects.requireNonNull(root);
        this.fn = Objects.requireNonNull(fn);
    }

    @Override
    public final Spliterator<T> trySplit()
    {
        return null;
    }

    @Override
    public final long estimateSize()
    {
        return Long.MAX_VALUE;
    }

    @Override
    public final int characteristics()
    {
        return ORDERED | NONNULL;
    }
}
