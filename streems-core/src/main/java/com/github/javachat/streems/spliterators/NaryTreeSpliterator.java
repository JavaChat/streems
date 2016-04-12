package com.github.javachat.streems.spliterators;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Function;

public abstract class NaryTreeSpliterator<T>
    implements Spliterator<T>
{
    protected final Function<T, Iterator<T>> fn;

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
