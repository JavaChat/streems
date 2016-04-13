package com.github.javachat.streems;

import com.github.javachat.streems.spliterators.BreadthFirstSpliterator;
import com.github.javachat.streems.spliterators.PostOrderSpliterator;
import com.github.javachat.streems.spliterators.PreOrderSpliterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The main class
 *
 * <p>Using this class requires that you have a Java class of some type {@code
 * T} with a method returning its immediate children, <em>in order</em>, which
 * are also of type {@code T}.</p>
 *
 * <p>This method can return either of:</p>
 *
 * <ul>
 *     <li>an {@link Iterator},</li>
 *     <li>an {@link Iterable} (recall that all {@link Collection}s are also
 *     iterables),</li>
 *     <li>an array.</li>
 * </ul>
 *
 * <p>Methods from this class will then assume that the first argument is the
 * root of an <a href="https://en.wikipedia.org/wiki/N-ary_tree"
 * target="_blank">n-ary tree</a> and provide a traversal of nodes of this tree
 * as a {@link Stream} using either a {@linkplain Traversal#PREORDER preorder},
 * a {@linkplain Traversal#POSTORDER postorder} or a {@linkplain
 * Traversal#BREADTHFIRST breadth first} traversal.</p>
 *
 * <p>Typically, you will pass the method which produces the iterator etc as a
 * method reference; for instance:</p>
 *
 * <pre>
 *     final Stream&lt;Foo&gt; stream = Streems.traverse(foo, Foo::children,
 *         Traversal.PREORDER);
 * </pre>
 */
public final class Streems
{
    private Streems()
    {
        throw new Error("no instantiation permitted");
    }

    /**
     * Traversal method used when the root provides an {@link Iterator} of its
     * children nodes
     *
     * @param root the root of the tree
     * @param provider the iterator provider
     * @param traversal the requested traversal order
     * @param <E> type of nodes in the tree
     * @return a stream of all nodes implementing the requested traversal
     *
     * @see IteratorProvider
     */
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
                spliterator = new PostOrderSpliterator<>(root, provider);
                break;
            case PREORDER:
                spliterator = new PreOrderSpliterator<>(root, provider);
                break;
            default:
                throw new IllegalStateException();
        }

        return StreamSupport.stream(spliterator, false);
    }

    /**
     * Traversal method used when the root provides an {@link Iterable} of its
     * children nodes
     *
     * @param root the root of the tree
     * @param provider the iterable provider
     * @param traversal the requested traversal order
     * @param <E> type of nodes in the tree
     * @return a stream of all nodes implementing the requested traversal
     *
     * @see IterableProvider
     */
    public static <E> Stream<E> traverse(final E root,
        final IterableProvider<E> provider, final Traversal traversal)
    {
        final IteratorProvider<E> p = e -> provider.apply(e).iterator();
        return traverse(root, p, traversal);
    }

    /**
     * Traversal method used when the root provides an array of its children
     * nodes
     *
     * @param root the root of the tree
     * @param provider the array provider
     * @param traversal the requested traversal order
     * @param <E> type of nodes in the tree
     * @return a stream of all nodes implementing the requested traversal
     *
     * @see ArrayProvider
     */
    public static <E> Stream<E> traverse(final E root,
        final ArrayProvider<E> provider, final Traversal traversal)
    {
        final IteratorProvider<E> p
            = e -> Arrays.stream(provider.apply(root)).iterator();
        return traverse(root, p, traversal);
    }
}
