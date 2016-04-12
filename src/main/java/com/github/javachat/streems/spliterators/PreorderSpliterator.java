package com.github.javachat.streems.spliterators;

import com.github.javachat.streems.NaryTreeSpliterator;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public final class PreorderSpliterator<T>
    implements NaryTreeSpliterator<T>
{
    private final Function<T, Iterator<T>> fn;
    private final Deque<Iterator<T>> deque = new ArrayDeque<>();

    public PreorderSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        this.fn = fn;
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

    @Override
    public int characteristics()
    {
        return ORDERED | NONNULL;
    }
}
