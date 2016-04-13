package com.github.javachat.streems.spliterators;

import java.util.ArrayDeque;
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
    private final Deque<T> deque = new ArrayDeque<>();

    public BreadthFirstSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        super(root, fn);
        deque.addFirst(root);
    }

    @Override
    public boolean tryAdvance(final Consumer<? super T> action)
    {
        if (deque.isEmpty())
            return false;
        final T element = deque.removeFirst();
        fn.apply(element).forEachRemaining(deque::addLast);
        action.accept(element);
        return true;
    }
}
