package com.github.javachat.streems.spliterators;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A spliterator for preorder traversal
 *
 * @param <T> type of the nodes in the tree
 */
public final class PreOrderSpliterator<T>
    extends NaryTreeSpliterator<T>
{
    private final Deque<Iterator<T>> deque = new ArrayDeque<>();

    public PreOrderSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        super(root, fn);
        deque.addLast(Collections.singleton(root).iterator());
    }

    @Override
    public boolean tryAdvance(final Consumer<? super T> action)
    {
        final Iterator<T> iterator = deque.getLast();
        if (iterator.hasNext()) {
            final T result = iterator.next();
            deque.addLast(fn.apply(result));
            action.accept(result);
            return true;
        }

        /*
         * No next value in iterator: remove it
         */
        deque.removeLast();
        return !deque.isEmpty() && tryAdvance(action);
    }
}
