package com.github.javachat.streems.spliterators;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A spliterator for breadth-first traversal
 *
 * @param <T> type of the nodes in the tree
 */
public final class BreadthFirstSpliterator<T>
    extends NaryTreeSpliterator<T>
{
    private final Deque<Iterator<T>> deque = new ArrayDeque<>();

    public BreadthFirstSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        super(root, fn);
        deque.addFirst(Collections.singleton(root).iterator());
    }

    @SuppressWarnings("TailRecursion")
    @Override
    public boolean tryAdvance(final Consumer<? super T> action)
    {
        final Iterator<T> iterator;
        final T element;

        if (deque.isEmpty())
            return false;

        iterator = deque.getFirst();
        if (!iterator.hasNext()) {
            deque.removeFirst();
            return tryAdvance(action);
        }

        element = iterator.next();
        deque.add(fn.apply(element));
        action.accept(element);
        return true;
    }
}
