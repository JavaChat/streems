package com.github.javachat.streems.spliterators;

import com.github.javachat.streems.NaryTreeSpliterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;

public final class BreadthFirstSpliterator<T>
    implements NaryTreeSpliterator<T>
{
    private final Function<T, Iterator<T>> fn;
    private final Deque<T> deque = new ArrayDeque<>();

    public BreadthFirstSpliterator(final T root,
        final Function<T, Iterator<T>> fn)
    {
        this.fn = fn;
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

    @Override
    public int characteristics()
    {
        return ORDERED | NONNULL;
    }
}
